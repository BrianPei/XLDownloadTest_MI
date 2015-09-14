package com.xunlei.download.utils.BTUtils;

import android.app.DownloadManager;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.TorrentFileInfo;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.XLConstant.XLErrorCode;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BTDownloadManager implements BTDownloadCfg {

    private ContentResolver mResolver;
    private Context mContext;
    private static BTDownloadManager mInstance;

    private BTDownloadManager(Context context) {
        mResolver = context.getContentResolver();
        mContext = context;
    }

    public static synchronized BTDownloadManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BTDownloadManager(context);
        }
        return mInstance;
    }

    public long enqueueFtp(String uri, File baseDir) {
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        uri = Uri.decode(uri);
        String fileName = uri.substring(uri.lastIndexOf('/') + 1);
        Uri destinationUri = Uri.withAppendedPath(Uri.fromFile(baseDir),
                fileName);
        ContentValues values = getCommonValues(destinationUri);
        values.put(COLUMN_URI, uri);

        values.put(COLUMN_TITLE, fileName);
        Uri downloadUri = mResolver.insert(CONTENT_URI,
                values);
        long id = Long.parseLong(downloadUri.getLastPathSegment());
        return id;
    }

    public long enqueueMagnet(String uri, File baseDir) {
        if (uri == null) {
            throw new NullPointerException("torrentInfo is null");
        }
        uri = Uri.decode(uri);

        String lastStr = uri.substring(MAGNET_PREFIX.length());
        int endOf = lastStr.indexOf('&');
        String title = lastStr.substring(0, endOf > 0 ? endOf : lastStr.length());
        Uri destinationUri = Uri.withAppendedPath(Uri.fromFile(baseDir),
                title + ".torrent");
        ContentValues values = getCommonValues(destinationUri);
        values.put(COLUMN_MIME_TYPE, MIME_TYPE_BT);
        values.put(COLUMN_URI, uri);

        values.put(COLUMN_TITLE, title);
        Uri downloadUri = mResolver.insert(CONTENT_URI,
                values);
        long id = Long.parseLong(downloadUri.getLastPathSegment());
        return id;
    }

    public long enqueueEmule(String uri, File baseDir) {
        if (uri == null) {
            throw new NullPointerException("uri is null");
        }
        uri = Uri.decode(uri);
        if (!uri.startsWith(EMULE_PREFIX)) {
            throw new IllegalArgumentException("not a ed2k");
        }
        String[] split = uri.split("\\|");
        String fileName = URLDecoder.decode(split[2]);
        Uri destinationUri = Uri.withAppendedPath(Uri.fromFile(baseDir),
                fileName);
        ContentValues values = getCommonValues(destinationUri);
        values.put(COLUMN_URI, uri);

        values.put(COLUMN_TITLE, fileName);
        values.put(COLUMN_TOTAL_BYTES, split[3]);
        Uri downloadUri = mResolver.insert(CONTENT_URI,
                values);
        long id = Long.parseLong(downloadUri.getLastPathSegment());
        return id;
    }

    public long enqueueBT(String uri, File baseDir, TorrentInfo torrentInfo, int[] fileIndex)
            throws IllegalAccessException {
        if (torrentInfo == null) {
            throw new NullPointerException("torrentInfo is null");
        }
        TorrentFileInfo[] fileInfo = torrentInfo.mSubFileInfo;
        if (fileInfo == null || fileInfo.length <= 0) {
            throw new IllegalArgumentException("SubFileInfo is null or Length less than 0");
        }
        HashSet<Integer> select = null;
        if (fileIndex != null) {
            if (fileInfo.length < fileIndex.length) {
                throw new IllegalAccessException("fileIndex out of range fileIndex is "
                        + fileIndex.length + " but SubFileInfo is" + fileInfo.length);
            }
            int length = fileIndex.length;
            select = new HashSet<>();
            for (int i = 0; i < length; i++) {
                if (fileIndex[i] > fileInfo.length) {
                    throw new IllegalAccessException("Without this file index " + fileIndex[i]);
                }
                select.add(Integer.valueOf(fileIndex[i]));
            }
        }
        String filesHash = new String(torrentInfo.mInfoHash);
        if (filesHash == null) {
            throw new IllegalArgumentException("InfoHash is null ");
        }

        if (torrentInfo.mFileCount != fileInfo.length) {
            throw new IllegalArgumentException("SubFileInfo information does not match");
        }
        if (baseDir == null || !baseDir.exists()) {
            throw new IllegalArgumentException("baseDir is null or not exists");
        }

        File path = new File(baseDir.getPath() + "/" + torrentInfo.mMultiFileBaseFolder);

        path = verifiedFileLength(path);

        Log.v("mandy", "path: " + path);

        if (!path.exists()) {
            path.mkdirs();
        }
        if (!path.exists()) {
            throw new IllegalArgumentException(path.getPath() + "not exists");
        }
        String title;
        if (TextUtils.isEmpty(torrentInfo.mMultiFileBaseFolder)) {
            String fileName = torrentInfo.mSubFileInfo[0].mFileName;
            title = fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            title = torrentInfo.mMultiFileBaseFolder;
        }
        long id = insertBt(uri, torrentInfo, filesHash, baseDir, title);
        int detailCount = 0;
        if (id > 0) {
            detailCount = insertBtDetail(id, filesHash, baseDir, title, torrentInfo.mSubFileInfo,
                    select);
        }
        return detailCount > 0 ? detailCount : id;
    }

    private long insertBt(String uri, TorrentInfo torrentInfo, String fileHash, File baseDir,
                          String title) {
        TorrentFileInfo[] fileInfo = torrentInfo.mSubFileInfo;
        if (fileInfo == null || fileInfo.length <= 0) {
            throw new IllegalArgumentException("SubFileInfo is null or Length less than 0");
        }

        Uri destinationUri = Uri.withAppendedPath(Uri.fromFile(baseDir),
                title);
        ContentValues values = new ContentValues();
        values.put(COLUMN_URI, "bt://" + uri);
        values.put(TORRENT_FILE_INFOS_HASH, fileHash);
        values.put(TORRENT_FILE_COUNT, torrentInfo.mFileCount);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_MIME_TYPE, MIME_TYPE_BT);
        values.putAll(getCommonValues(destinationUri));
        Uri downloadUri = mResolver.insert(CONTENT_URI,
                values);
        long id = Long.parseLong(downloadUri.getLastPathSegment());
        return id;
    }

    private int insertBtDetail(long btId, String fileHash, File baseDir,
                               String title, TorrentFileInfo[] fileInfo,
                               HashSet<Integer> select) {
        int count = 0;
        if (btId < 0 || fileInfo == null || fileInfo.length <= 0 || select == null
                || select.size() < 0) {
            return count;
        }

        List<ContentValues> valuesSet = new ArrayList<>();

        String rootPath = baseDir.getPath() + "/" + title;
        String subPath;
        String fileName;
        String path;
        for (int i = 0; i < fileInfo.length; i++) {
            TorrentFileInfo torrentFileInfo = fileInfo[i];
            if (select == null || select.contains(torrentFileInfo.mFileIndex)) {
                ContentValues value = new ContentValues();
                fileName = torrentFileInfo.mFileName;
                value.put(TORRENT_FILE_NAME, fileName);
                subPath = torrentFileInfo.mSubPath;
                value.put(TORRENT_SUB_PATH, subPath);
                path = rootPath + (TextUtils.isEmpty(subPath) ? "" : "/" + subPath) + "/"
                        + fileName;
                value.put(TORRENT_DATA, path);
                value.put(TORRENT_STATUS, STATUS_RUNNING);
                value.put(TORRENT_FILE_INDEX, torrentFileInfo.mFileIndex);
                value.put(TORRENT_FILE_SIZE, torrentFileInfo.mFileSize);
                value.put(TORRENT_FILE_INFOS_HASH, fileHash);
                value.put(TORRENT_DOWNLOAD_ID, btId);
                valuesSet.add(value);
            }
        }
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        try {
            long step = 100;
            long i = 0;
            int size = valuesSet.size();
            for (ContentValues contentValues : valuesSet) {
                ContentProviderOperation op = ContentProviderOperation.newInsert(URI_BT_DETAIL)
                        .withValues(contentValues).build();
                ops.add(op);
                if (i % step == 0 || i == (size - 1)) {
                    ContentProviderResult[] result = mResolver.applyBatch("downloads", ops);
                    count += result.length;
                    ops.clear();
                }
                i++;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        return count;

    }

    private ContentValues getCommonValues(Uri uri) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_PUBLIC_API, true);
        values.put(COLUMN_NOTIFICATION_PACKAGE, mContext.getPackageName());
        values.put(COLUMN_VISIBILITY, DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        values.put(COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI, true);
        values.put(COLUMN_ALLOWED_NETWORK_TYPES, ~0);
        if (uri != null) {
            values.put(COLUMN_DESTINATION,
                    DESTINATION_FILE_URI);
            values.put(COLUMN_FILE_NAME_HINT,
                    uri.toString());
        }
        return values;
    }

    public static synchronized TorrentInfo getTorrentInfo(XLDownloadManager downloadlib,
                                                          String btFilePath) {
        TorrentInfo torrentInfo = new TorrentInfo();
        if (downloadlib != null) {
            int ret = downloadlib.getTorrentInfo(btFilePath, torrentInfo);
            if (ret != XLErrorCode.NO_ERROR) {
                return null;
            }
        }
        return torrentInfo;
    }

    public static File verifiedFileLength(File origin) {
        if (origin == null) {
            return null;
        }
        String absolutePath = origin.getAbsolutePath();
        int charSize = calcTheStringCharSize(absolutePath);
        int TOTAL_SIZE = 255;
        if (charSize < TOTAL_SIZE) {
            //绝对字符的长度小于255的话,可以使用原来的
            return origin;
        }
        //按照最后一个反斜杠 把绝对路径分割成两部分,把最后一部分切割
        int lastIndex = absolutePath.lastIndexOf("/") + 1;
        String firstPath = absolutePath.substring(0, lastIndex);
        String lastPath = absolutePath.substring(lastIndex);

        try {
            int loopSize = 0;
            while (true) {
                loopSize++;
                //把最后一部分对半截取
                int lastPathLength = lastPath.length() / 2;
                String lastFirst = lastPath.substring(0, lastPathLength);
                lastPath = lastFirst;
                String tmpAbsolutePath = firstPath + lastPath;
                if (calcTheStringCharSize(tmpAbsolutePath) < TOTAL_SIZE) {
                    return new File(tmpAbsolutePath);
                }
                if (loopSize > 50) {
                    //容错处理
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new File(firstPath);
    }

    private static int calcTheStringCharSize(String absolutePath) {
        //取出所有中文的字符串
        StringBuilder chineseChar = new StringBuilder();
        StringBuilder englishChar = new StringBuilder();
        for (int i = 0; i < absolutePath.length(); i++) {
            String chartmp = String.valueOf(absolutePath.charAt(i));
            if (chartmp.matches("[\\u4e00-\\u9fa5]")) {
                chineseChar.append(chartmp);
            } else {
                englishChar.append(chartmp);
            }
        }
        return 2 * chineseChar.length() + englishChar.length();
    }
}
