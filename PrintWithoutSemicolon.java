public class PrintWithoutSemicolon {
    
    public static void main(String[] args) {
        
        // print anything without using Semicolon:
        if(System.out.printf("Succesdfully printed without using semicolon 1")==null ) {

        }

        // second method to print without :
        if(System.out.append("\nSuccessfully printed without using semicolon 2")==null) {

        }

        // third method to print without :
        if(System.out.printf("\nPrinted without Semicolon 3").equals(null)) {

        }

        // fourth method:
        for(int i =0; i<1; System.out.print("\nprinted without using semicolon 4"))
        {
            i++;
        }
        
    }
    
}
