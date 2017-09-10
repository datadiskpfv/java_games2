package uk.co.datadisk.slidingtiles;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        boolean accept = false;
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png") || file.isDirectory()) {
            accept = true;
        }
        return accept;
    }

    @Override
    public String getDescription() {
        return "*.jpg, *.gif, *.png";
    }
}