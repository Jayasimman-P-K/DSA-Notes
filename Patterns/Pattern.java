package Patterns;

public class Pattern {

    static void pattern1(int n) {
        for ( int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                System.out.print("* ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    static void pattern2(int n) {
        for (int i = 0; i<n; i++){
            for (int j =0; j < i+1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    static void pattern3 (int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(j+1+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern4 (int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(i+1+" ");
            }
            System.out.println();
        }        
        System.out.println();
    }

    static void pattern5 (int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern6 (int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print(j+1 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern11 (int n) {
        for (int i =0; i<n; i++) {
            boolean bool;
            if (i%2 == 0) {
                    bool = true;
                } else {
                    bool = false;
                }
            for (int j = 0; j < i+1; j++) {
                System.out.print((bool ? 1 : 0) + " ");
                bool = !bool;
            }
            
            System.out.println();
        }
        System.out.println();
    }

    static void pattern13 (int n) {
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(count + " ");
                count++;
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern14 (int n) {
        char a = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print((char)(a + j)+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern15 (int n) {
        char a = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                System.out.print((char)(a + j)+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern16 (int n) {
        char a = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print((char)(a + i)+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern17 (int n) {
        char a = 'A';
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print((char)(a + n - i + j - 1)+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void pattern21 (int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == n-1 || j == n-1) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();

        /* 

        *****
        *   *
        *   *
        *   *
        *****

        */
    }

    static void patternK1(int n) {

        for(int i = 0; i<(2*n); i++) {
            int colCount = i<n ? i : (2*n)-i; 
            for (int j = 0; j < colCount; j++) {
                System.out.print("* ");
            }
            System.out.println("");
        }
        System.out.println();

        /* 

        *
        * * 
        * * *
        * * * *
        * * * * *
        * * * *
        * * *
        * *
        *

        */
    }

    static void zohoPattern1(String word) {
        for(int i = 0; i<word.length(); i++) {
            for(int j = 0; j<word.length(); j++) {
                if (i==j) {
                    System.out.print(word.charAt(i));
                } else if (i+j == word.length() - 1) {
                    System.out.print(word.charAt(word.length() - i -1));
                }else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println(" ");

        int n = 5;
        pattern1(n);
        pattern2(n);
        pattern3(n);
        pattern4(n);
        pattern5(n);
        pattern6(n);
        pattern11(n);
        pattern13(n);
        pattern14(n);
        pattern15(n);
        pattern16(n);
        pattern17(n);
        pattern21(n);

        // Patterns from Kunal Kushwaha
        patternK1(n);

        // Zoho previous Pattern Question
        zohoPattern1("Program");
    }
}