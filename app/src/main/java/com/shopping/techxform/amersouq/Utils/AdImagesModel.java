package com.shopping.techxform.amersouq.Utils;

import android.net.Uri;

/**
 * Created by techxform on 04-Jan-19.
 */

public class AdImagesModel {

    private String image_id;
    private String file_path;
    private Uri image_uri;


    public AdImagesModel(String image_id, String file_path, Uri image_uri) {
        this.image_id = image_id;
        this.file_path = file_path;
        this.image_uri = image_uri;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Uri getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(Uri image_uri) {
        this.image_uri = image_uri;
    }
}
