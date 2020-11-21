package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class serFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".ser");
    }

    @Override
    public String getDescription() {
        return ".ser";
    }
}
