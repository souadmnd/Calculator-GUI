import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {

    // ==== Variables ====
    private JTextField display;
    private JButton ac;
    private JButton backspaceButton;
    private JButton percentButton;
    private JButton divideButton;
    private JButton number7;
    private JButton number8;
    private JButton number9;
    private JButton multiplyButton;
    private JButton number4;
    private JButton number5;
    private JButton number6;
    private JButton subtractButton;
    private JButton number1;
    private JButton number2;
    private JButton number3;
    private JButton addButton;
    private JButton number0;
    private JButton decimalButton;
    private JButton signButton;
    private JButton equalsButton;

    private double firstNumber;
    private double secondNumber;
    private double result;

    private String operator;

    private boolean calculationComplete = false;
    private boolean errorDisplayed = false;

    private final DecimalFormat df = new DecimalFormat("0.##########");

    private final Font normalFont = new Font("Arial", Font.PLAIN, 30);
    private final Font errorFont = new Font("Arial", Font.PLAIN, 12);

    // ==== Constructor ====
    public Calculator() {

        // Configure the main window
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create the calculator display
        JPanel textFieldPanel = new JPanel(new BorderLayout());
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(normalFont);
        display.setEditable(false);

        textFieldPanel.add(display, BorderLayout.CENTER);

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ac = new JButton("AC");

        URL imageURL = getClass().getResource("/delete.png");

        if (imageURL != null) {
            backspaceButton = new JButton(new ImageIcon(imageURL));
        } else {
            backspaceButton = new JButton("⌫");
            System.out.println("Delete image not found!");
        }

        percentButton = new JButton("%");
        divideButton = new JButton("/");

        number7 = new JButton("7");
        number8 = new JButton("8");
        number9 = new JButton("9");
        multiplyButton = new JButton("x");

        number4 = new JButton("4");
        number5 = new JButton("5");
        number6 = new JButton("6");
        subtractButton = new JButton("-");

        number1 = new JButton("1");
        number2 = new JButton("2");
        number3 = new JButton("3");
        addButton = new JButton("+");

        number0 = new JButton("0");
        decimalButton = new JButton(".");
        signButton = new JButton("+/-");
        equalsButton = new JButton("=");

        equalsButton.setBackground(Color.ORANGE);
        ac.setBackground(new Color(211, 47, 47));
        ac.setForeground(Color.WHITE);

        // Add buttons to the panel
        buttonsPanel.add(ac);
        buttonsPanel.add(backspaceButton);
        buttonsPanel.add(percentButton);
        buttonsPanel.add(divideButton);
        buttonsPanel.add(number7);
        buttonsPanel.add(number8);
        buttonsPanel.add(number9);
        buttonsPanel.add(multiplyButton);
        buttonsPanel.add(number4);
        buttonsPanel.add(number5);
        buttonsPanel.add(number6);
        buttonsPanel.add(subtractButton);
        buttonsPanel.add(number1);
        buttonsPanel.add(number2);
        buttonsPanel.add(number3);
        buttonsPanel.add(addButton);
        buttonsPanel.add(number0);
        buttonsPanel.add(decimalButton);
        buttonsPanel.add(signButton);
        buttonsPanel.add(equalsButton);

        ac.addActionListener(this);
        backspaceButton.addActionListener(this);
        signButton.addActionListener(this);
        percentButton.addActionListener(this);
        divideButton.addActionListener(this);
        number7.addActionListener(this);
        number8.addActionListener(this);
        number9.addActionListener(this);
        multiplyButton.addActionListener(this);
        number4.addActionListener(this);
        number5.addActionListener(this);
        number6.addActionListener(this);
        subtractButton.addActionListener(this);
        number1.addActionListener(this);
        number2.addActionListener(this);
        number3.addActionListener(this);
        addButton.addActionListener(this);
        number0.addActionListener(this);
        decimalButton.addActionListener(this);
        equalsButton.addActionListener(this);

        // Add components to the window
        this.add(textFieldPanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);

        // Display the window
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // ==== Main Method ====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }

    // ==== Reset Method ====
    private void resetCalculator() {

        display.setFont(normalFont);
        display.setText("0");

        firstNumber = 0;
        secondNumber = 0;
        result = 0;

        operator = null;

        calculationComplete = false;
        errorDisplayed = false;
    }

    // ==== Action listener ====
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        // Handle AC button clicks
        if (e.getSource() == ac) {
            resetCalculator();
            return;
        }

        // Handle error state
        if (errorDisplayed) {
            if (command.matches("[0-9]") || command.equals(".")) {
                display.setText("0");
                display.setFont(normalFont);
                errorDisplayed = false;
                calculationComplete = false;
            } else {
                return;
            }
        }

        // Handle number button clicks
        if (command.matches("[0-9]")) {
            if (calculationComplete) {
                display.setText(command);
                calculationComplete = false;

            } else if (display.getText().equals("0")) {
                display.setText(command);

            } else {
                display.setText(display.getText() + command);
            }

            return;
        }

        // Handle decimal button clicks
        if (command.equals(".")) {
            if (calculationComplete) {
                display.setText("0.");
                calculationComplete = false;

            } else if (display.getText().contains(command)) {
                return;

            } else if (display.getText().equals("0")) {
                display.setText("0.");

            } else {
                display.setText(display.getText() + command);
            }

            return;
        }

        // Handle sign button clicks
        if (e.getSource() == signButton) {

            String currentText = display.getText();

            if (currentText.equals("0")) {
                return;
            }

            if (currentText.startsWith("-")) {
                display.setText(currentText.substring(1));
            } else {
                display.setText("-" + currentText);
            }
            return;
        }

        // Handle backspace button clicks
        if (e.getSource() == backspaceButton) {

            String currentText = display.getText();

            if (currentText.equals("0")) {
                return;
            }

            String newText = currentText.substring(0, currentText.length() - 1);

            if (newText.equals("-") || newText.isEmpty()) {
                display.setText("0");
                return;
            }

            display.setText(newText);

            return;
        }

        // Handle arithmetic operations button clicks (+ - x /)
        if (e.getSource() == addButton ||
                e.getSource() == subtractButton ||
                e.getSource() == multiplyButton ||
                e.getSource() == divideButton) {

            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            display.setText("0");
            calculationComplete = false;

            return;
        }

        // ---equals button---
        if (e.getSource() == equalsButton) {

            if (operator == null) {
                return;
            }

            secondNumber = Double.parseDouble(display.getText());

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "-":
                    result = firstNumber - secondNumber;
                    break;

                case "x":
                    result = firstNumber * secondNumber;
                    break;

                case "/":
                    if (secondNumber == 0) {
                        display.setText("Cannot divide by zero");
                        display.setFont(errorFont);
                        errorDisplayed = true;
                        operator = null;
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;

                default:
                    return;

            }

            display.setText(df.format(result));
            calculationComplete = true;
            operator = null;

            return;
        }

        // Handle percentage button clicks
        if (e.getSource() == percentButton) {
            double currentNumber = Double.parseDouble(display.getText());
            result = currentNumber / 100;
            display.setText(df.format(result));
            calculationComplete = true;
            return;
        }
    }
}
