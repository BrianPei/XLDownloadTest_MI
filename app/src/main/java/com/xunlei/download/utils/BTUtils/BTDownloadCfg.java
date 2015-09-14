package com.xunlei.download.utils.BTUtils;

import android.app.DownloadManager;
import android.net.Uri;
import android.provider.Downloads;

public interface BTDownloadCfg {
    public static final String TAG = "BTDownloadThread";
    // mTaskStatus： 0 表示idle； 1表示running；2表示success；3表示failed；4表示stopped
    public static final int TASK_STATU_IDLE = 0;
    public static final int TASK_STATU_RUNNING = 1;
    public static final int TASK_STATU_SUCCESS = 2;
    public static final int TASK_STATU_FAILED = 3;
    public static final int TASK_STATU_STOPPED = 4;

    public static final String TABLE_BT_DETAIL = "bt_detail";
    public static final String TORRENT_FILE_INFOS_HASH = "torrent_file_infos_hash";
    public static final String TORRENT_FILE_INFOS = "torrent_file_infos";
    public static final String TORRENT_FILE_COUNT = "torrent_file_count";
    public static final String TORRENT_ID = Downloads.Impl._ID;
    public static final String TORRENT_DOWNLOAD_ID = "download_id";
    public static final String TORRENT_CURRENT_SIZE = "current_size";
    public static final String TORRENT_FILE_SIZE = "torrent_file_size";
    public static final String TORRENT_FILE_NAME = "torrent_file_name";
    public static final String TORRENT_SUB_PATH = "torrent_sub_path";
    public static final String TORRENT_DATA = "torrent_data";
    public static final String TORRENT_FILE_INDEX = "torrent_file_index";
    public static final String TORRENT_P2S_SPEED = "p2s_speed";
    public static final String TORRENT_DOWNLOAD_SPEED = "download_speed";
    public static final String TORRENT_STATUS = DownloadManager.COLUMN_STATUS;

    public static final Uri URI_BT_DETAIL = Uri
            .parse("content://downloads/all_downloads_download_bt_detail");

    public static final Uri CONTENT_URI = Downloads.Impl.CONTENT_URI;
    public static final String COLUMN_APP_DATA = Downloads.Impl.COLUMN_APP_DATA;
    public static final String _DATA = Downloads.Impl._DATA;
    public static final String COLUMN_MIME_TYPE = Downloads.Impl.COLUMN_MIME_TYPE;
    public static final String COLUMN_VISIBILITY = Downloads.Impl.COLUMN_VISIBILITY;
    public static final String COLUMN_DESTINATION = Downloads.Impl.COLUMN_DESTINATION;
    public static final String COLUMN_CONTROL = Downloads.Impl.COLUMN_CONTROL;
    public static final String COLUMN_STATUS = Downloads.Impl.COLUMN_STATUS;
    public static final String COLUMN_LAST_MODIFICATION = Downloads.Impl.COLUMN_LAST_MODIFICATION;
    public static final String COLUMN_NOTIFICATION_PACKAGE = Downloads.Impl.COLUMN_NOTIFICATION_PACKAGE;
    public static final String COLUMN_NOTIFICATION_CLASS = Downloads.Impl.COLUMN_NOTIFICATION_CLASS;
    public static final String COLUMN_NOTIFICATION_EXTRAS = Downloads.Impl.COLUMN_NOTIFICATION_EXTRAS;
    public static final String COLUMN_TOTAL_BYTES = Downloads.Impl.COLUMN_TOTAL_BYTES;
    public static final String COLUMN_CURRENT_BYTES = Downloads.Impl.COLUMN_CURRENT_BYTES;
    public static final String COLUMN_TITLE = Downloads.Impl.COLUMN_TITLE;
    public static final String COLUMN_DESCRIPTION = Downloads.Impl.COLUMN_DESCRIPTION;
    public static final String COLUMN_URI = Downloads.Impl.COLUMN_URI;
    public static final String COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI = Downloads.Impl.COLUMN_IS_VISIBLE_IN_DOWNLOADS_UI;
    public static final String COLUMN_FILE_NAME_HINT = Downloads.Impl.COLUMN_FILE_NAME_HINT;
    public static final String COLUMN_MEDIAPROVIDER_URI = Downloads.Impl.COLUMN_MEDIAPROVIDER_URI;
    public static final String COLUMN_DELETED = Downloads.Impl.COLUMN_DELETED;
    public static final String COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT = Downloads.Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT;
    public static final String COLUMN_ALLOWED_NETWORK_TYPES = Downloads.Impl.COLUMN_ALLOWED_NETWORK_TYPES;
    public static final String COLUMN_NO_INTEGRITY = Downloads.Impl.COLUMN_NO_INTEGRITY;
    public static final String COLUMN_FAILED_CONNECTIONS = Downloads.Impl.COLUMN_FAILED_CONNECTIONS;
    public static final String COLUMN_COOKIE_DATA = Downloads.Impl.COLUMN_COOKIE_DATA;
    public static final String COLUMN_USER_AGENT = Downloads.Impl.COLUMN_USER_AGENT;
    public static final String COLUMN_REFERER = Downloads.Impl.COLUMN_REFERER;
    public static final String COLUMN_OTHER_UID = Downloads.Impl.COLUMN_OTHER_UID;
    public static final String COLUMN_IS_PUBLIC_API = Downloads.Impl.COLUMN_IS_PUBLIC_API;
    public static final int DESTINATION_FILE_URI = Downloads.Impl.DESTINATION_FILE_URI;

    public static final int STATUS_PENDING = Downloads.Impl.STATUS_PENDING;
    public static final int STATUS_RUNNING = Downloads.Impl.STATUS_RUNNING;
    public static final int STATUS_PAUSED_BY_APP = Downloads.Impl.STATUS_PAUSED_BY_APP;
    public static final int STATUS_SUCCESS = Downloads.Impl.STATUS_SUCCESS;
    public static final int STATUS_CANCELED = Downloads.Impl.STATUS_CANCELED;
    public static final int STATUS_BAD_REQUEST = Downloads.Impl.STATUS_BAD_REQUEST;
    public static final int STATUS_CANNOT_RESUME = Downloads.Impl.STATUS_CANNOT_RESUME;
    public static final int STATUS_FILE_ERROR = Downloads.Impl.STATUS_FILE_ERROR;
    public static final int STATUS_HTTP_DATA_ERROR = Downloads.Impl.STATUS_HTTP_DATA_ERROR;
    public static final int STATUS_TOO_MANY_REDIRECTS = Downloads.Impl.STATUS_TOO_MANY_REDIRECTS;
    public static final int STATUS_WAITING_FOR_NETWORK = Downloads.Impl.STATUS_WAITING_FOR_NETWORK;
    public static final int STATUS_WAITING_TO_RETRY = Downloads.Impl.STATUS_WAITING_TO_RETRY;

    public static final String MIME_TYPE_BT = "application/x-bittorrent";

    public static final String MAGNET_PREFIX = "magnet:?xt=urn:btih:";
    public static final String FTP_PREFIX = "ftp:";
    public static final String EMULE_PREFIX = "ed2k:";

    // 查询索引状态，0初始状态不查询索引，1正在查询索引，2查询到索引信息，3未查询到有效索引信息
    public static final int QUERY_INDEX_STATUS_INIT = 0;
    public static final int QUERY_INDEX_STATUS_DOING = 1;
    public static final int QUERY_INDEX_STATUS_HAVE = 2;
    public static final int QUERY_INDEX_STATUS_NO = 3;
}
