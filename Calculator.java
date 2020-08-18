import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    JTextField result = new JTextField("",20); //演算結果の格納
    double stackedValue = 0.0; 
    boolean isStacked = false; //stackedValueに値が入っているか
    boolean afterCalc = false; //演算子が押されているか
    String currentOp = ""; //押した演算子の格納

    public static void main(String args[]){
        Calculator frame = new Calculator("電卓");
        frame.setVisible(true); //ウィンドウの表示
    }

    Calculator(String title){
        setTitle(title);
        setBounds(100, 100, 380, 350); //ウィンドウの大きさ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ウィンドウを閉じたときの処理

        JPanel p = new JPanel();
        JPanel key = new JPanel();
        JPanel clear = new JPanel();
        
        p.add(result);
        key.add(new NumberButton("7"),0);
        key.add(new NumberButton("8"),1);
        key.add(new NumberButton("9"),2);
        key.add(new CalcButton("/"),3);
        key.add(new NumberButton("4"),4);
        key.add(new NumberButton("5"),5);
        key.add(new NumberButton("6"),6);
        key.add(new CalcButton("*"),7);
        key.add(new NumberButton("1"),8);
        key.add(new NumberButton("2"),9);
        key.add(new NumberButton("3"),10);
        key.add(new CalcButton("-"),11);
        key.add(new NumberButton("0"),12);
        key.add(new NumberButton("."),13);
        key.add(new CalcButton("="), 14);
        key.add(new CalcButton("+"),15);
        clear.add(new ClearButton());

        key.setLayout(new GridLayout(4,4)); //4*4にボタン配置
        clear.setLayout(new GridLayout(1,1));
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);//数字を表示するためのもの
        contentPane.add(key, BorderLayout.CENTER); //数字と演算子ボタンを表示するためのもの
        contentPane.add(clear, BorderLayout.SOUTH); //clearボタンを表示するためのもの
        }

        /*数字ボタンを押したとき */
        public class NumberButton extends JButton implements ActionListener {
            public NumberButton(String keyTop){
            super(keyTop);
            this.addActionListener(this);
        }
        public void actionPerformed(ActionEvent evt) {
            String keyNumber = this.getText();
            appendResult(keyNumber);
        }
    }

    public void appendResult(String c) {
        if( ! afterCalc ){
            result.setText(result.getText() + c);
        }
        else {
            result.setText(c);
            afterCalc =false;
        }
    }

    /*clearボタンを押したとき */
    public class ClearButton extends JButton implements ActionListener {
        public ClearButton() {
            super("C");
            this.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            result.setText("");
            stackedValue = 0.0;
            isStacked = false;
            boolean afterCalc = false;
            currentOp = "";
        }
    }

    /*演算子ボタンを押したとき */
    public class CalcButton extends JButton implements ActionListener{
        public CalcButton(String op){
            super(op);
            this.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){
            if(isStacked){ //stackedValueに値が入っている時
                double resultValue = (Double.valueOf(result.getText())).doubleValue();
            if ( currentOp.equals("+"))
                stackedValue += resultValue;
            else if( currentOp.equals("-"))
                stackedValue -= resultValue;
            else if( currentOp.equals("*"))
                stackedValue *= resultValue;
            else if( currentOp.equals("/"))
                stackedValue /= resultValue;
                result.setText(String.valueOf(stackedValue));
            }

            currentOp = this.getText(); //演算子保管
            stackedValue = (Double.valueOf(result.getText())).doubleValue();
            afterCalc =true;

            if( currentOp.equals("計算")){
                isStacked = false;
            }
            else {
                isStacked = true;
            }
        }
    }
}
