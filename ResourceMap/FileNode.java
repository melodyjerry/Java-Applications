package org.example.file;

import java.util.ArrayList;

/**
 * @ClassName: file.FileNode
 * @Description: 储存文件的结点信息
 * @version: v1.0.0
 * @author: linovce
 * @date: 2020/4/29 9:33
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/4/29      linovce           v1.0.0               修改原因
 */
public class FileNode {
    private String fileName;
    private ArrayList<FileNode> subFiles;

    public FileNode(String fileName){
        this.fileName=fileName;
        this.subFiles=null;
    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<FileNode> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(ArrayList<FileNode> subFiles) {
        this.subFiles = subFiles;
    }
}
