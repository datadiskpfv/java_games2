package uk.co.datadisk.imageresizer;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        boolean accept = false;
        String fileName = f.getName().toLowerCase();
        if (fileName.endsWith(".jpg") || f.isDirectory()) {
            accept = true;
        }
        return accept;
    }

    @Override
    public String getDescription() {
        return "*.jpg";
    }
}