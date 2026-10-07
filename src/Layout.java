import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Layout implements ActionListener {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta; //typing area
    private JTextArea input;
    private int WIDTH=800;
    private int HEIGHT=700;
    private String text;
    private String everyoneShutUp = "";
    private boolean isRunning;
    private JLabel inputLable;
    private JScrollPane sp; // Stack overflow



    public Layout() {

        isRunning = true;
        prepareGUI();
    }

    public static void main(String[] args) {
        Layout swingControlDemo = new Layout();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(3,1));

        //menu at top



        ta = new JTextArea(); // area that you can type in
        sp = new JScrollPane(ta);
        ta.setBounds(50, 10, WIDTH-100, HEIGHT-50);
        input = new JTextArea();
        input.setBounds(50,100,WIDTH-100,HEIGHT-50);
        ta.setEditable(false);
        input.append("                  ");

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        inputLable = new JLabel("Input:");
        inputLable.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel(); // panel is an invisiable area put on the mainframe for a specific purposes
        controlPanel.setLayout(new FlowLayout()); //set the layout of the pannel - Flow small + in middle

        // BorderLayout - 5 zones (N,S,E,W,Center)

        //put things in order - order matters


        mainFrame.add(statusLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(sp);
       // mainFrame.add(ta);//add typing area


        mainFrame.setVisible(true);





    }

    private void showEventDemo() {


        JButton submitButton = new JButton("Submit");
        JButton ResetButton = new JButton("Reset");
        JButton allCapsButton = new JButton("All Caps");

        submitButton.setActionCommand(text);
        ResetButton.setActionCommand("");

        submitButton.addActionListener(new ButtonClickListener());
        ResetButton.addActionListener(new ButtonClickListener());
        allCapsButton.addActionListener(new ButtonClickListener());


        controlPanel.add(inputLable);
        controlPanel.add(input);




        controlPanel.add(submitButton);
        controlPanel.add(ResetButton);
        controlPanel.add(allCapsButton);

        mainFrame.setVisible(true); // every time a change happens
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();



            if (command.equals("All Caps")) {
                statusLabel.setText("Ok Button clicked.");
                ta.append(text.toUpperCase()+"\n");

            } else if (command.equals("Submit")) {

                text = input.getText();
                ta.append(text.trim()+"\n"); //stack overflow



            } else {
                ta.selectAll();
                ta.replaceSelection("");
                input.selectAll();
                input.replaceSelection("                  ");

            }
        }

    }
}