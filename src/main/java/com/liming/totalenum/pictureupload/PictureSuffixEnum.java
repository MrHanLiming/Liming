package com.liming.totalenum.pictureupload;

/**
 * 
 */
public enum PictureSuffixEnum {

    PNG(".png","/image"),
    JPG(".jpg","/image"),
    GIT(".git","/image"),
    BMP(".bmp","/image"),
    TXT(".txt","/documents"),
    JS(".js","/documents"),
    JSON(".json","/documents"),
    CVS(".cvs","/documents");

    private String suffix;
    private String catalog;

    PictureSuffixEnum(String suffix, String catalog) {
        this.suffix = suffix;
        this.catalog = catalog;
    }

    public static PictureSuffixEnum getPictureSuffix(String suffix){
        for (PictureSuffixEnum pictureSuffix : PictureSuffixEnum.values()){
            if (pictureSuffix.suffix.equals(suffix))
                return pictureSuffix;
        }
        return null;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
}
