import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculator extends JFrame {
    JTextField result = new JTextField("",20); //���Z���ʂ̊i�[
    double stackedValue = 0.0; 
    boolean isStacked = false; //stackedValue�ɒl�������Ă��邩
    boolean afterCalc = false; //���Z�q��������Ă��邩
    String currentOp = ""; //���������Z�q�̊i�[

    public static void main(String args[]){
        Calculator frame = new Calculator("�d��");
        frame.setVisible(true); //�E�B���h�E�̕\��
    }

    Calculator(String title){
        setTitle(title);
        setBounds(100, 100, 380, 350); //�E�B���h�E�̑傫��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�E�B���h�E������Ƃ��̏���

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

        key.setLayout(new GridLayout(4,4)); //4*4�Ƀ{�^���z�u
        clear.setLayout(new GridLayout(1,1));
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);//������\�����邽�߂̂���
        contentPane.add(key, BorderLayout.CENTER); //�����Ɖ��Z�q�{�^����\�����邽�߂̂���
        contentPane.add(clear, BorderLayout.SOUTH); //clear�{�^����\�����邽�߂̂���
        }

        /*�����{�^�����������Ƃ� */
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

    /*clear�{�^�����������Ƃ� */
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

    /*���Z�q�{�^�����������Ƃ� */
    public class CalcButton extends JButton implements ActionListener{
        public CalcButton(String op){
            super(op);
            this.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e){
            if(isStacked){ //stackedValue�ɒl�������Ă��鎞
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

            currentOp = this.getText(); //���Z�q�ۊ�
            stackedValue = (Double.valueOf(result.getText())).doubleValue();
            afterCalc =true;

            if( currentOp.equals("�v�Z")){
                isStacked = false;
            }
            else {
                isStacked = true;
            }
        }
    }
}
