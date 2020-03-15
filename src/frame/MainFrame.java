package frame;

import pojo.Record;
import service.FindService;
import service.LotteryService;
import service.RegisterService;
import service.impl.FindServiceImpl;
import service.impl.LotteryServiceImpl;
import service.impl.RegisterServiceImpl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	private FindService findService=new FindServiceImpl();
	private LotteryService lotteryService=new LotteryServiceImpl();
	private RegisterService registerService=new RegisterServiceImpl();
	private int orderId;

	public String name;
	public String identity;
	public String phone;
	public int quantity;
	private JPanel contentPane;
	JButton beginButton;
	JButton endButton;

	/**
	 * 初始化
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		Container pane = this.getContentPane();
		pane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		/**
		 * 预约设置版块
		 */
		JPanel RerservationPanel = new JPanel();
		RerservationPanel.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180), 3, true),
			" 预约设置 ", TitledBorder.CENTER, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 22),
			SystemColor.activeCaptionBorder));
		RerservationPanel.setBackground(Color.WHITE);

		pane.add(RerservationPanel);
		RerservationPanel.setLayout(new GridLayout(0, 1, 0, 0));

		//预约设置版块
		JPanel maskPanel = new JPanel();
		maskPanel.setBackground(Color.WHITE);
		RerservationPanel.add(maskPanel);
		maskPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel totalMask = new JLabel("口罩总数");
		maskPanel.add(totalMask);
		totalMask.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		TextField maskTextField = new TextField();
		maskPanel.add(maskTextField);
		maskTextField.setColumns(20);
		maskTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		//按钮
		JPanel buttonPanel1 = new JPanel();
		buttonPanel1.setBackground(Color.WHITE);
		RerservationPanel.add(buttonPanel1);
		buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

		beginButton = new JButton("开始预约");
		beginButton.setHorizontalAlignment(SwingConstants.LEFT);
		buttonPanel1.add(beginButton);
		beginButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		beginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mask=maskTextField.getText();
				if(mask==null || mask.length()<1) {
					JOptionPane.showMessageDialog(contentPane, "请输入口罩总数！", "提示",JOptionPane.WARNING_MESSAGE);  
				}else {
					try{
						int maskNum=Integer.parseInt(mask);
						if(maskNum<0) {
							throw new NumberFormatException();
						}
						orderId=lotteryService.startOder(maskNum);
						beginButton.setEnabled(false);
						endButton.setEnabled(true);
					}catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(contentPane, "口罩数目必须是正整数！", "提示",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		beginButton.setBackground(SystemColor.activeCaption);

		endButton = new JButton("结束预约");
		endButton.setEnabled(false);
		buttonPanel1.add(endButton);
		endButton.setBackground(SystemColor.activeCaption);
		endButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lotteryService.endOrder(orderId);
				beginButton.setEnabled(true);
				endButton.setEnabled(false);
			}
		});

		/**
		 * 登记信息版块
		 */
		JPanel InformationPanel = new JPanel();
		InformationPanel.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180), 3, true),
			" 登记信息 ", TitledBorder.CENTER, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 22),
			SystemColor.activeCaptionBorder));
		InformationPanel.setBackground(Color.WHITE);
		pane.add(InformationPanel);
		InformationPanel.setLayout(new BoxLayout(InformationPanel, BoxLayout.Y_AXIS));

		JPanel subPanel = new JPanel();
		InformationPanel.add(subPanel);
		subPanel.setBackground(Color.WHITE);
		subPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel subPanel1 = new JPanel();
		subPanel.add(subPanel1);
		subPanel1.setBackground(Color.WHITE);
		subPanel1.setLayout(new GridLayout(0, 1, 0, 0));

		//真实姓名
		JPanel namePanel = new JPanel();
		subPanel1.add(namePanel);
		FlowLayout flowLayout = (FlowLayout) namePanel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.LEFT);
		namePanel.setBackground(Color.WHITE);

		JLabel nameLabel = new JLabel("真实姓名");
		namePanel.add(nameLabel);
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		JTextField nameTextField = new JTextField();
		namePanel.add(nameTextField);
		nameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		nameTextField.setColumns(10);

		//预约数量
		JPanel quantityPanel = new JPanel();
		FlowLayout flowLayout1 = (FlowLayout) quantityPanel.getLayout();
		flowLayout1.setVgap(10);
		flowLayout1.setHgap(10);
		flowLayout1.setAlignment(FlowLayout.LEFT);
		subPanel1.add(quantityPanel);
		quantityPanel.setBackground(Color.WHITE);

		JLabel quantityLabel = new JLabel("预约数量");
		quantityPanel.add(quantityLabel);
		quantityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		JComboBox quantityTextField = new JComboBox();
		quantityTextField.setMaximumRowCount(5);
		quantityPanel.add(quantityTextField);
		quantityTextField.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3"}));
		quantityTextField.setSelectedIndex(2);
		quantityTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		quantityTextField.setBackground(Color.WHITE);

		JPanel subPanel2 = new JPanel();
		subPanel.add(subPanel2);
		subPanel2.setBackground(Color.WHITE);
		subPanel2.setLayout(new GridLayout(0, 1, 0, 0));

		//身份证号
		JPanel identityPanel = new JPanel();
		FlowLayout flowLayout2 = (FlowLayout) identityPanel.getLayout();
		flowLayout2.setVgap(10);
		flowLayout2.setHgap(10);
		flowLayout2.setAlignment(FlowLayout.LEFT);
		subPanel2.add(identityPanel);
		identityPanel.setBackground(Color.WHITE);

		JLabel identityLabel = new JLabel("身份证号");
		identityPanel.add(identityLabel);
		identityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		JTextField identityTextField = new JTextField();
		identityPanel.add(identityTextField);
		identityTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		identityTextField.setColumns(10);

		//手机号码
		JPanel phonePanel = new JPanel();
		FlowLayout flowLayout3 = (FlowLayout) phonePanel.getLayout();
		flowLayout3.setVgap(10);
		flowLayout3.setHgap(10);
		flowLayout3.setAlignment(FlowLayout.LEFT);
		subPanel2.add(phonePanel);
		phonePanel.setBackground(Color.WHITE);

		JLabel phoneLabel = new JLabel("手机号码");
		phonePanel.add(phoneLabel);
		phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		JTextField phoneTextField = new JTextField();
		phonePanel.add(phoneTextField);
		phoneTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		phoneTextField.setColumns(10);

		//提交按钮
		JPanel buttonPanel2 = new JPanel();
		buttonPanel2.setBackground(Color.WHITE);
		InformationPanel.add(buttonPanel2);

		JButton submitButton = new JButton("提交");
		buttonPanel2.add(submitButton);
		submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		submitButton.setBackground(SystemColor.activeCaption);

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(beginButton.isEnabled()){
					JOptionPane.showMessageDialog(contentPane, "请先开启预约！", "提示",JOptionPane.WARNING_MESSAGE);
				}
				String name=nameTextField.getText();
				String uid=identityTextField.getText();
				String telNum=phoneTextField.getText();
				String mask=quantityTextField.getSelectedItem().toString();

				if("".equals(name) || "".equals(uid) || "".equals(telNum) || "".equals(mask)){
					JOptionPane.showMessageDialog(contentPane, "请填写完整信息！", "提示",JOptionPane.WARNING_MESSAGE);
				}else {
					try{
						int maskNum=Integer.parseInt(mask);
						if(maskNum<0) {
							throw new NumberFormatException();
						}
						if(registerService.canRegister(uid, telNum, orderId))
						{
							int recordId = registerService.Register(uid, telNum,name, orderId, maskNum);
							JOptionPane.showMessageDialog(contentPane, "你的预约编号为："+recordId, "预约成功",JOptionPane.WARNING_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(contentPane, "身份证或手机号已被使用或"
								+"手机号或者身份证号在此前三次预约中成功中签","预约失败",JOptionPane.WARNING_MESSAGE);
						}
					}catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(contentPane, "口罩数目必须是正整数！", "提示",JOptionPane.WARNING_MESSAGE);
					}
				}


			}
		});

		/**
		 * 中签查询版块
		 */
		JPanel SearchPanel = new JPanel();
		SearchPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		SearchPanel.setBorder(new TitledBorder(new LineBorder(new Color(180, 180, 180), 3, true),
			" 中签查询 ", TitledBorder.CENTER, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 22),
			SystemColor.activeCaptionBorder));
		SearchPanel.setBackground(Color.WHITE);

		pane.add(SearchPanel);
		SearchPanel.setLayout(new BoxLayout(SearchPanel, BoxLayout.Y_AXIS));

		//预约编号
		JPanel idPanel = new JPanel();
		idPanel.setBackground(Color.WHITE);
		SearchPanel.add(idPanel);
		idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel idLabel = new JLabel("预约编号");
		idPanel.add(idLabel);
		idLabel.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		TextField idTextField = new TextField();
		idPanel.add(idTextField);
		idTextField.setColumns(20);
		idTextField.setFont(new Font("微软雅黑", Font.PLAIN, 22));

		//查询按钮
		JPanel buttonPanel3 = new JPanel();
		buttonPanel3.setBackground(Color.WHITE);
		SearchPanel.add(buttonPanel3);

		JButton searchButton = new JButton("查询");
		buttonPanel3.add(searchButton);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setAlignmentY(Component.TOP_ALIGNMENT);
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		searchButton.setBackground(SystemColor.activeCaption);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String uOrderId=idTextField.getText();
				if("".equals(uOrderId)) {
					JOptionPane.showMessageDialog(contentPane, "请输入预约编号！", "提示",JOptionPane.WARNING_MESSAGE);  
				}
				else {
					try{
						int oId=Integer.parseInt(uOrderId);
						Record record = findService.getRecord(oId);
						if(record!=null && record.isSelected()){
							InformationFrame info=new InformationFrame(record);
							info.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(contentPane, "你没有中签或记录不存在", "提示",JOptionPane.WARNING_MESSAGE);
						}
					}catch (NumberFormatException ex){
						JOptionPane.showMessageDialog(contentPane, "预约编号必须是正整数！", "提示",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

	}



	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}
