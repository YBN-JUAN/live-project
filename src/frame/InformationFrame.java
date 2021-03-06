package frame;

import pojo.Record;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationFrame extends JFrame {
    private JPanel contentPane;
    private Record record;

    public InformationFrame(Record record) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true),
            "预约信息", TitledBorder.CENTER, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 22),
            Color.LIGHT_GRAY));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("成功中签！您的预约编号：" + record.getId());
        idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setFont(new Font("幼圆", Font.BOLD, 22));
        idLabel.setForeground(Color.GRAY);
        contentPane.add(idLabel);

        JPanel subPanel = new JPanel();
        subPanel.setBackground(Color.WHITE);
        contentPane.add(subPanel);
        subPanel.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel subPanel1 = new JPanel();
        subPanel1.setBackground(Color.WHITE);
        subPanel.add(subPanel1);
        subPanel1.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        subPanel1.add(namePanel);

        JLabel nameLabel = new JLabel("真实姓名");
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        namePanel.add(nameLabel);

        JLabel name = new JLabel(record.getUserName());
        namePanel.add(name);

        JPanel quantityPanel = new JPanel();
        quantityPanel.setBackground(Color.WHITE);
        subPanel1.add(quantityPanel);

        JLabel quantityLabel = new JLabel("预约数量");
        quantityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        quantityPanel.add(quantityLabel);

        JLabel orderNum = new JLabel(Integer.toString(record.getOrderNum()));
        quantityPanel.add(orderNum);

        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(Color.WHITE);
        subPanel.add(subPanel2);
        subPanel2.setLayout(new GridLayout(0, 1, 0, 0));

        JPanel identityPanel = new JPanel();
        identityPanel.setBackground(Color.WHITE);
        subPanel2.add(identityPanel);

        JLabel identityLabel = new JLabel("身份证号");
        identityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        identityPanel.add(identityLabel);

        JLabel userId = new JLabel(record.getUserId());
        identityPanel.add(userId);

        JPanel phonePanel = new JPanel();
        phonePanel.setBackground(Color.WHITE);
        subPanel2.add(phonePanel);

        JLabel phoneLabel = new JLabel("手机号码");
        phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        phonePanel.add(phoneLabel);

        JLabel Telnum = new JLabel(record.getTelNum());
        phonePanel.add(Telnum);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel);
        buttonPanel.setBackground(Color.WHITE);

//        JButton confirmButton = new JButton("确认");
//        confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
//        confirmButton.setBackground(SystemColor.activeCaption);
//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        buttonPanel.add(confirmButton);
    }
}
