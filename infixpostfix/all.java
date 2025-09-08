package infixpostfix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InfixPostfix converter = new InfixPostfix( ) ;
        Scanner sc = new Scanner(System.in) ;

        System.out.println("Infix → Postfix (+ Calculate)") ;
        System.out.print("Please Input Expression: ") ;
        String expr = sc.nextLine() ;

        converter.infixToPostfix(expr) ;

        sc.close() ;
    }
}

----------------------------------------------------------------

  package infixpostfix;
import java.util.Scanner;

public class InfixPostfix {
    private Stack strStack = new Stack(50); // เก็บ operator
    private Priority pr = new Priority();
    Scanner sc = new Scanner(System.in);

    // Infix → Postfix + Evaluate
    public void infixToPostfix(String expr) {
        String[] output = new String[100]; // เก็บ postfix
        int outIndex = 0; 

        System.out.printf("%-10s %-15s %-15s\n", "Input", "Stack", "Output");
        System.out.println("----------------------------------------");


        for (char c : expr.toCharArray()) {
            int priority = pr.incoming(c);

            if (priority == 0) { // operand (ตัวเลขหรืออักษร)
                output[outIndex++] = c + ""; // เพิ่มตัวเลขหรืออักษร
                output[outIndex++] = " "; 
            } else if (c == '(') {
                strStack.push(c + "");
            } else if (c == ')') {
                while (!strStack.isEmpty() && !strStack.peek().equals("(")) {
                    output[outIndex++] = strStack.pop();
                    output[outIndex++] = " ";
                }
                strStack.pop(); // ลบ '('
            } else { // operator
                while (!strStack.isEmpty() &&
                        priority <= pr.inStack(strStack.peek().charAt(0))) {
                    output[outIndex++] = strStack.pop();
                    output[outIndex++] = " ";
                }
                strStack.push(c + "");
            }

            // แสดงตารางขั้นตอน
            System.out.printf("%-10s %-15s ", c + "", strStack.getAll());
            for (int i = 0; i < outIndex; i++)
                System.out.print(output[i]);
            System.out.println();
        }

        // pop operator เหลือทั้งหมด
        while (!strStack.isEmpty()) {
            output[outIndex++] = strStack.pop();
            output[outIndex++] = " ";
        }

        // สร้าง String postfix
        String postfix = "";
        for (int i = 0; i < outIndex; i++)
            postfix += output[i];


        System.out.println("Final Postfix: " + postfix);

        // คำนวณจาก postfix
        int result = evaluatePostfix(postfix);
        System.out.println("Final Result: " + result);
    }

    // Evaluate postfix (ตัวเลขคำนวณได้ ตัวอักษรไม่คำนวณ)
    private int evaluatePostfix(String postfix) {
        StackInt intStack = new StackInt(50);

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) { // ตัวเลข
                intStack.push(c - '0'); // แปลง char  int
            } else if (Character.isLetter(c)) { // ตัวอักษร
            
                int val = -1;
                // ป้อนค่าจากมือ
                if (true) {
                    System.out.print("Enter value = " + c + ": ");
                    val = sc.nextInt();
                }
                intStack.push(val);
                
            } else if (c == ' ') { // เว้นวรรค
                continue;
            } else { // operator
                if (intStack.size() >= 2) { // ถ้ามี operand เพียงพอ
                    int r = intStack.pop();
                    int l = intStack.pop();
                    intStack.push(pr.calculate(c, l, r));
                }
                // ถ้า operand ไม่พอ → skip
            }
        }

        if (!intStack.isEmpty())
            return intStack.pop(); // ผลลัพธ์
        return 0; // ถ้าไม่มีตัวเลข
    }
}


--------------------------------------------------------------------------------

  package infixpostfix;

public class Priority {
    
    public int incoming(char c) {
        if (Character.isLetterOrDigit(c))
            return 0;
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
                return 4;
            case ')':
                return 6;
            default:
                return 5; // invalid
        }
    }

    public int inStack(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            case '(':
                return 0;
            case ')':
                return 6;
            default:
                return 5;
        }
    }

    public int calculate(char op, int l, int r) {
        switch (op) {
            case '+':
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            case '/':
                return l / r;
            case '^':
                return (int)Math.pow(l,r);
            default:
                return -1;
        }
    }
}


-------------------------------------------------------------------

  package infixpostfix;

public class Stack {
    String[] arr;
    int top = -1;

    public Stack(int size) {
        arr = new String[size];
    }

    void push(String item) {
        arr[++top] = item;
    }

    String pop() {
        return arr[top--];
    }

    String peek() {
        return arr[top];
    }

    boolean isEmpty() {
        return top == -1;
    }

    int size() {
        return top + 1;
    }

    // แปลง getAll เป็น array ต่อ string เอง
    String getAll() {
        String s = "";
        for (int i = 0; i <= top; i++)
            s += arr[i] + " ";
        return s.trim();
    }
}


------------------------------------------------------------------------

  package infixpostfix;

public class StackInt {
    int[] arr;
    int top = -1;

    public StackInt(int size) {
        arr = new int[size];
    }

    void push(int item) {
        arr[++top] = item;
    }

    int pop() {
        return arr[top--];
    }

    boolean isEmpty() {
        return top == -1;
    }

    int size() {
        return top + 1;
    }

    int peek() {
        return arr[top];
    }
}


----------------------------------------------------------
