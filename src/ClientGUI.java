import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ClientGUI extends VBox {

    private Label smtpLabel;
    private TextField smtpTextField;
    private Label hostLabel;
    private TextField hostTextField;
    private Label portLabel;
    private TextField portTextField;
    private Label senderLabel;
    private TextField senderTextField;
    private Label recipientLabel;
    private TextField recipientTextField;
    private TextArea txtArea;
    private Button button;
    private Button FileAttachbutton;
    private Label statusLabel;

    private void setPane() {
        // VBox container
        VBox vbox = new VBox(10); // spacing between elements

        // SMTP Command
        smtpLabel = new Label("SMTP COMMAND");
        smtpTextField = new TextField();
        VBox layout0 = new VBox(smtpLabel, smtpTextField);

        // Host-name and port number
        hostLabel = new Label("HOST: ");
        hostTextField = new TextField();
        VBox layout1 = new VBox(hostLabel, hostTextField);

        portLabel = new Label("PORT NUM: ");
        portTextField = new TextField();
        VBox layout2 = new VBox(portLabel, portTextField);

        // Sender name and recipient name input
        senderLabel = new Label("SENDER NAME: ");
        senderTextField = new TextField("sender@csc2b.uj.zc.za");
        VBox layout3 = new VBox(senderLabel, senderTextField);

        recipientLabel = new Label("RECIPIENT NAME: ");
        recipientTextField = new TextField("receiver@csc2b.uj.zc.za");
        VBox layout4 = new VBox(recipientLabel, recipientTextField);

        // Creating the text Area for the Email
        txtArea = new TextArea();
        VBox layout5 = new VBox(txtArea);

        // Send button
        button = new Button("SEND EMAIL");
        statusLabel = new Label();
        
        //Attaching file button
        FileAttachbutton = new Button("ATTACH FILE");
        

        vbox.getChildren().addAll(layout0, layout1, layout2, layout3, layout4, layout5, button, statusLabel, FileAttachbutton);

        getChildren().add(vbox);
    }

    public ClientGUI() {
        setPane();
        //Setting the action of an attachment button
        FileAttachbutton.setOnAction(even->{
        	//Object of choosing a file
        	FileChooser filechooser = new FileChooser(); 
        	filechooser.setTitle("Open a File");
        	filechooser.setInitialDirectory(new File("C:"));
        	filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        	File selectfile =  filechooser.showOpenDialog(null);
        	
        });
        //Setting the Action of the button
        button.setOnAction(event -> {
            try {
                sendEmail();
                statusLabel.setText("Email sent successfully.");
            } catch (Exception e) {
                statusLabel.setText("Error: " + e.getMessage());
            }
        });
    }

    //send email function
    private void sendEmail() throws Exception {
        String host = hostTextField.getText();
        int port = Integer.parseInt(portTextField.getText());
        String from = senderTextField.getText();
        String to = recipientTextField.getText();
        String subject = smtpTextField.getText();
        String body = txtArea.getText();

        SmptEmail emailSender = new SmptEmail(host, port);
        emailSender.sendEmail(from, to, subject, body);
    }
}
