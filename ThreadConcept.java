
class FileDownload extends Thread {
    private String fileName;

    FileDownload(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println(fileName + " downloading: "
                        + (i * 20) + "%");
                Thread.sleep(1000);
            }
            System.out.println(fileName + " Download Complete!");
        } catch (InterruptedException e) {
            System.out.println(fileName + " interrupted!");
            Thread.currentThread().interrupt();
        }
    }
}

public class ThreadConcept {
    public static void main(String[] args) {
        FileDownload file1 = new FileDownload("File A");
        FileDownload file2 = new FileDownload("File B");
        FileDownload file3 = new FileDownload("File C");

        file1.start();
        file2.start();
        file3.start();
    }
}
/*
 * Java Multithreading allows a program to perform multiple tasks at the same
 * time.
 * 
 * In this program, three threads download three different files simultaneously.
 * - start() – Starts a new thread.
 * - run() – Defines the task.
 * - sleep() – Pauses a thread for 1 second.
 * Real-world example: Downloading multiple files at the same time.
 */