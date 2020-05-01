package org.example.file;

import org.xmind.core.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DirectoryOperation {
    // 创建思维导图的工作空间
    static IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
    static IWorkbook workbook = workbookBuilder.createWorkbook();
    // 获得默认sheet
    static ISheet primarySheet = workbook.getPrimarySheet();

    public ArrayList<String> getFiles(String path){
        ArrayList<String> files = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for(int i=0;i<tempList.length;i++){
            if(tempList[i].isFile()){
                //System.out.println(tempList[i].toString());
                files.add(tempList[i].toString());
            }
        }

        return files;
    }

    public ArrayList<String> getFolder(String path){
        ArrayList<String> directorys = new ArrayList<>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for(int i=0;i<tempList.length;i++){
            if(tempList[i].isDirectory()){
                //System.out.println(tempList[i].toString());
                directorys.add(tempList[i].toString());
            }
        }

        return directorys;
    }

    public FileNode getAll(String path){
        FileNode root = new FileNode(path);
        ArrayList<FileNode> subfiles = new ArrayList<>();
        ArrayList<String> folders = getFolder(path);
        ArrayList<String> files = getFiles(path);

        for(String tempPath : folders){
            if(tempPath.endsWith(".svn"))
                continue;
            FileNode folder = getAll(tempPath);
            subfiles.add(folder);
        }

        for(String tempFile : files){
            FileNode file = new FileNode(tempFile);
            subfiles.add(file);
        }
        root.setSubFiles(subfiles);

        return root;
    }

    public ITopic printAll(FileNode root) throws IOException, CoreException {

        int index = root.getFileName().lastIndexOf("\\");
        String name = root.getFileName().substring(index+1);
        ITopic topic = workbook.createTopic();
        topic.setTitleText(name);

        if(root.getSubFiles()==null){
            return topic;
        }

        for(FileNode file : root.getSubFiles()){
            ITopic chapterTopic = printAll(file);
            topic.add(chapterTopic,ITopic.ATTACHED);
        }

        return topic;
    }

    public static void main(String[] args) throws IOException, CoreException{
        DirectoryOperation directoryOperation = new DirectoryOperation();
        FileNode root = directoryOperation.getAll("E:\\WeChatFiles");


        ITopic tt = directoryOperation.printAll(root);
        primarySheet.replaceRootTopic(tt);


        // 保存
        workbook.save("D:\\test.png");
    }
}