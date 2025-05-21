package Lab2;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

public class ConvertorApp {
    static ArrayList<String> conversionItems = new ArrayList<>();
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);

        ArrayList<String> items = new ArrayList<>();

        JComboBox<String> comboBox2 = new JComboBox<>(ConvertorApp.conversionItems.toArray(new String[0]));
        comboBox2.setBounds(300, 20, 200, 30);
        comboBox2.setEnabled(false);
        frame.add(comboBox2);

        frame.setLayout(null);
        for (String key : Unit.units.keySet()) {
            items.add(key);
        }
        JComboBox<String> comboBox = new JComboBox<>(items.toArray(new String[0]));
        comboBox.setBounds(20, 20, 200, 30);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String selectedItem = (String) comboBox.getSelectedItem();
                MeasurmentType type = Unit.units.get(selectedItem).measurmentType;
                ConvertorApp.conversionItems = ItemGetter(type);

                comboBox2.setModel(new DefaultComboBoxModel<>(ConvertorApp.conversionItems.toArray(new String[0])));

                comboBox2.setEnabled(true);
            }
        });
        frame.add(comboBox);


        var textField = new TextField();
        textField.setBounds(20, 60, 200, 30);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        frame.add(textField);


        


        var label = new JLabel("Here will be your answer");
        label.setBounds(300, 60, 200, 30);
        frame.add(label);


        var convertButton = new JButton("Convert");
        convertButton.setBounds(20, 300, 500, 30);
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                double converted = Unit.Convert(Double.parseDouble(textField.getText()), comboBox.getSelectedItem().toString(), comboBox2.getSelectedItem().toString());
                label.setText(String.valueOf(converted));
            }
        });
        frame.add(convertButton);


        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static ArrayList<String> ItemGetter(MeasurmentType type){
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Unit> entry : Unit.units.entrySet()) {
            Unit unit = entry.getValue();
            if (unit.measurmentType == type) {
                String key = entry.getKey(); 
                result.add(key);
            }
        }
        return result;
    }


}