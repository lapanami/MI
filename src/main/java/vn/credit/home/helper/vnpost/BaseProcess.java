package vn.credit.home.helper.vnpost;

import vn.credit.home.helper.vnpost.ExcelHandle;
import vn.credit.home.helper.vnpost.Trace;
import vn.credit.home.helper.vnpost.TraceInput;
import vn.credit.home.helper.vnpost.JSplitButton;
import vn.credit.home.helper.vnpost.GeneralUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public abstract class BaseProcess extends JPanel{

	//private JFrame frmVnpostTrackAnd;
	private JTextField txtName;
	private JTextField txtCol;
	private JLabel lblCol;
	private JButton btnTrace;
	
	public JButton getBtnTrace() {
		return btnTrace;
	}

	private DefaultTableModel model;
	/**
	 * Create the application.
	 */
	private boolean allowRemove;
//	public BaseProcess(boolean allowRemove, final MainForm mainForm) {
//		this.allowRemove = allowRemove;
//		setBounds(100, 100, 800, 400);
//		setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel = new JPanel();
//		add(panel, BorderLayout.SOUTH);
//		panel.setPreferredSize(new Dimension(0, 25));
//		panel.setLayout(new BorderLayout(0, 0));
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setPreferredSize(new Dimension(100, 0));
//		panel.add(panel_1, BorderLayout.EAST);
//		panel_1.setLayout(null);
//		
//		lblCount = new JLabel("");
//		lblCount.setBounds(10, 6, 64, 14);
//		panel_1.add(lblCount);
//		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		add(tabbedPane, BorderLayout.CENTER);
//		
//		JPanel pnlTrace = new JPanel();
//		tabbedPane.addTab("Track and trace", null, pnlTrace, null);
//		pnlTrace.setLayout(new BorderLayout(0, 0));
//		
//		JPanel pnlTop = new JPanel();
//		pnlTop.setPreferredSize(new Dimension(0, 70));
//		pnlTrace.add(pnlTop, BorderLayout.NORTH);
//		pnlTop.setLayout(null);
//		
//		txtName = new JTextField();
//		txtName.setEnabled(false);
//		txtName.setBounds(10, 11, 325, 20);
//		pnlTop.add(txtName);
//		txtName.setColumns(10);
//		
//		btnChoose = new JButton("Choose");
//		btnChoose.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				chooseFile();
//			}
//		});
//		btnChoose.setBounds(345, 10, 78, 23);
//		pnlTop.add(btnChoose);
//		
//		btnTrace = new JButton("Trace");
//		btnTrace.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				trace();
//			}
//		});
//		btnTrace.setEnabled(false);
//		btnTrace.setBounds(521, 10, 89, 23);
//		pnlTop.add(btnTrace);
//		
//		btnLoad = new JButton("Load");
//		btnLoad.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				loadIntoGrid();
//			}
//		});
//		btnLoad.setEnabled(false);
//		btnLoad.setBounds(427, 10, 89, 23);
//		pnlTop.add(btnLoad);
//		
//		chckbxFollow = new JCheckBox("Follow the output stream");
//		chckbxFollow.setSelected(true);
//		chckbxFollow.setBounds(10, 40, 200, 23);
//		pnlTop.add(chckbxFollow);
//		
//		//hungnvv add
//		txtCol = new JTextField("1");
//		txtCol.setEnabled(true);
//		txtCol.setBounds(260, 40, 20, 20);
//		pnlTop.add(txtCol);
//		txtCol.setColumns(1);
//		
//		lblCol = new JLabel("Get row");
//		lblCol.setBounds(210, 40, 50, 20);
//		pnlTop.add(lblCol);
//		
//		JSplitButton btnAddNewTab = new JSplitButton("Add new tab");		
//		
//		JPopupMenu popupMenu = new JPopupMenu("Tracer mode");
//		JMenuItem item = new JMenuItem("KHL");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("KHL");				
//			}
//		});
//		
//		item = new JMenuItem("EMS");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("EMS");				
//			}
//		});
//		
//		//hungnvv add more 2 menu
//		/*
//		item = new JMenuItem("KHL Input");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("KHL Input");				
//			}
//		});
//		
//		item = new JMenuItem("KHL Output");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("KHL Output");				
//			}
//		});
//		*/
//		item = new JMenuItem("KHL Special");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("KHL Special");				
//			}
//		});
//		
//		item = new JMenuItem("KHL Special 2");
//		popupMenu.add(item);
//		item.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.addNewTab("KHL Special 2");				
//			}
//		});
//		
//		//end
//		
//		btnAddNewTab.setComponentPopupMenu(popupMenu);
//		btnAddNewTab.setBounds(352, 40, 107, 23);
//		pnlTop.add(btnAddNewTab);
//		
//		btnRemoveThisTab = new JButton("Remove this tab");
//		btnRemoveThisTab.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				mainForm.removeTab(BaseProcess.this);
//			}
//		});
//		btnRemoveThisTab.setEnabled(allowRemove);
//		btnRemoveThisTab.setBounds(469, 40, 116, 23);
//		pnlTop.add(btnRemoveThisTab);
//		
//		btnExport = new JButton("Export");
//		btnExport.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				export();
//			}
//		});
//		btnExport.setBounds(615, 10, 89, 23);
//		pnlTop.add(btnExport);
//		
//		model = new DefaultTableModel();
//		header = getTableHeaders();
//		model.setDataVector(null, header);
//		
//		scrollPane = new JScrollPane();
//		pnlTrace.add(scrollPane, BorderLayout.CENTER);
//		tblTrace = buildTable(model);	
//		tblTrace.setRowHeight(20);
//		tblTrace.setAutoCreateRowSorter(false);
//
//		scrollPane.setViewportView(tblTrace);
//		
//		LineNumberTableRowHeader tableLineNumber = new LineNumberTableRowHeader(scrollPane, tblTrace);
//		tableLineNumber.setBackground(Color.LIGHT_GRAY);
//		scrollPane.setRowHeaderView(tableLineNumber);
//	}
	
	public abstract JTable buildTable(DefaultTableModel model);
	public abstract String[] getTableHeaders();
	
//	protected void export() {
//		int count = tblTrace.getRowCount();
//		Object[][] data = new Object[count + 1][header.length];
//		data[0] = header;
//		
//		for(int i = 0; i< count; i++){
//			for(int j =0; j<header.length; j++){
//				data[i+1][j] = tblTrace.getValueAt(i, j);
//			}
//		}
//		try {
//			
//			JFileChooser fileChooser = new JFileChooser(selectedFile);  
//			int option = fileChooser.showSaveDialog(null);
//			if(option == JFileChooser.APPROVE_OPTION){
//				if(fileChooser.getSelectedFile()!=null){
//					File theFileToSave = fileChooser.getSelectedFile();
//					
//					ExcelHandle excelHandle = new ExcelHandle(theFileToSave);
//					excelHandle.writeExcelFile(data, getName(), header.length);
//					JOptionPane.showMessageDialog(null, theFileToSave.getAbsolutePath() + " is saved");
//				}
//				 
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public abstract Thread createThread();

	private Thread t;
	protected void trace() {
		httpclient = null;
		btnLoad.setEnabled(false);

		if(t==null){
			t = createThread();
		}
		
		if(btnTrace.getText().equals("Trace")){
			t = null;
			t = createThread();
			t.start();
			btnChoose.setEnabled(false);
		}
		else{
			btnChoose.setEnabled(true);
			if(t!=null && t.isAlive()){
				t.stop();
			}
			t=null;
		}

		btnTrace.setText(btnTrace.getText().equals("Trace") ? "Stop" : "Trace");
		
	}

	private Object[] header;
	
	protected void loadIntoGrid() {
		
		try{
			model.setDataVector(null, header);
			ExcelHandle excelHandle = new ExcelHandle(selectedFile);
	
			for(Object[] row: excelHandle.read()){
				model.addRow(row);
			}
			btnLoad.setEnabled(false);
			btnTrace.setEnabled(true);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private File selectedFile;
	private JButton btnLoad;
	private JTable tblTrace;
	private JLabel lblCount;
	
	
	public JLabel getLblCount() {
		return lblCount;
	}

	private JScrollPane scrollPane;
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	private JCheckBox chckbxFollow;
	public JCheckBox getChckbxFollow() {
		return chckbxFollow;
	}

	private JButton btnChoose;
	public JButton getBtnChoose() {
		return btnChoose;
	}
	
	private JTextField txtRow;
	public JTextField getTxtRow()
	{
		return txtCol;
	}

	private JButton btnRemoveThisTab;
	private JButton btnExport;
	protected void chooseFile() {
		if(selectedFile==null)
			selectedFile = new File(".");
		JFileChooser fileopen = new JFileChooser(selectedFile);
		fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Excel 1997-2000", "xls"));
		fileopen.setAcceptAllFileFilterUsed(true);

	    int ret = fileopen.showDialog(null, "Open file");

	    if (ret == JFileChooser.APPROVE_OPTION) {
	    	selectedFile = fileopen.getSelectedFile();
	    	txtName.setText(selectedFile.getAbsolutePath());
	    	btnLoad.setEnabled(true);
	    }
	}
	
 
	private final String USER_AGENT = "Mozilla/5.0";
	private DefaultHttpClient httpclient;
	
	public DefaultHttpClient getHttpClient(){
		if(httpclient!=null) 
			return httpclient;
		httpclient = new DefaultHttpClient();
		httpclient.getAuthSchemes().register("ntlm", new NTLMSchemeFactory());
		Properties properties = GeneralUtil.readConfig();
		String username = properties.getProperty("config.account.config.window.username");
		String password = properties.getProperty("config.account.config.window.password");
		httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, new NTCredentials(username, password, "", ""));
		HttpHost proxy = new HttpHost("vnhqpry02.hcnet.vn", 8080);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		httpclient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
		httpclient.getAuthSchemes().register("ntlm", new NTLMSchemeFactory());
		
		return httpclient;
	}
	
	public String sendPost(String url, List<NameValuePair> urlParameters) throws ClientProtocolException, IOException{
		httpclient = getHttpClient();
		
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", USER_AGENT);		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		HttpResponse response = httpclient.execute(post);
		
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		if(code == 407){
			throw new ClientProtocolException("Invalid window acccount. Please check your domain account");
		}
		
		String content = renderResponeToString(response);
		EntityUtils.consume(response.getEntity());
		
		post.abort();
		
		return content;
	}
	
	public String sendGet(String url) throws ClientProtocolException, IOException{
		DefaultHttpClient httpclient = getHttpClient();
		
		HttpGet get = new HttpGet(url);
		get.setHeader("User-Agent", USER_AGENT);		
		
		HttpResponse response = httpclient.execute(get);
		
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		if(code == 407){
			throw new ClientProtocolException("Invalid window acccount. Please check your domain account");
		}
		String content = renderResponeToString(response);
		EntityUtils.consume(response.getEntity());
		
		return content;
	}
	
	private String renderResponeToString(HttpResponse response) throws IllegalStateException, IOException{
		InputStreamReader in = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
		try{
			
			BufferedReader rd = new BufferedReader(in);
			 
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line + "\n");
			}
	 
			return result.toString();
		}
		catch(IllegalStateException ex){
			ex.printStackTrace();
			throw ex;
		}
		catch(IOException ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			in.close();
		}
	}
	
	public abstract String getResultTable(String postCode) throws Exception;		
	public abstract List<Trace> decodeTrace(String table) throws Exception;
	//public abstract List<TraceInput> decodeTraceInput(String table) throws Exception;
}
