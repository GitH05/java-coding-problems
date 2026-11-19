
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
