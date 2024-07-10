import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainX implements ActionListener {
    String requestToken;

    JFrame frame;
    JButton btlp, btli, btlo, btp, btt, btv, badd, bsend, bdel;
    JPanel panelBL, panel, panell, panello, panelp, panelt, panellp, panelup;
    CardLayout cardLayout;
    JTextField vtf;
    JTextArea uinp, del, tweet;

    MainX() {
        frame = new JFrame();
        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setTitle("X With JAVA");

        JLabel jdl = new JLabel("X JAVA");
        jdl.setFont(new Font("San Francisco", Font.BOLD, 45));
        jdl.setBounds(270, 2, 160, 100);
        jdl.setForeground(Color.WHITE);

        btlp = new JButton("LOGIN");
        btlp.setFont(new Font("CALIBRE", Font.BOLD, 18));
        btlp.setForeground(Color.BLACK);
        btlp.setBackground(Color.BLACK);
        btlp.setOpaque(false);
        btlp.setBorder(new LineBorder(Color.BLACK));
        btlp.setBorderPainted(true);
        btlp.setBounds(250, 200, 60, 40);

        btli = new JButton("LOGIN");
        btli.setFont(new Font("CALIBRE", Font.BOLD, 18));
        btli.setForeground(Color.WHITE);
        btli.setBackground(Color.BLACK);
        btli.setOpaque(false);
        btli.setBorder(new LineBorder(Color.BLACK));
        btli.setBorderPainted(true);
        btli.setBounds(5, 10, 90, 50);

        btp = new JButton("PROFILE");
        btp.setFont(new Font("CALIBRE", Font.BOLD, 18));
        btp.setForeground(Color.WHITE);
        btp.setBackground(Color.BLACK);
        btp.setOpaque(false);
        btp.setBorder(new LineBorder(Color.BLACK));
        btp.setBorderPainted(true);
        btp.setBounds(5, 58, 90, 50);

        btt = new JButton("POST");
        btt.setFont(new Font("", Font.BOLD, 18));
        btt.setForeground(Color.WHITE);
        btt.setBackground(Color.BLACK);
        btt.setOpaque(false);
        btt.setBorder(new LineBorder(Color.BLACK));
        btt.setBorderPainted(true);
        btt.setBounds(5, 108, 90, 50);

        btlo = new JButton("LOGOUT");
        btlo.setFont(new Font("Helvetica CY", Font.BOLD, 18));
        btlo.setForeground(Color.WHITE);
        btlo.setBackground(Color.RED);
        btlo.setOpaque(false);
        btlo.setBorder(new LineBorder(Color.BLACK));
        btlo.setBorderPainted(true);
        btlo.setBounds(5, 158, 90, 50);

        panelBL = new JPanel();
        panelBL.setBackground(Color.BLACK);
        panelBL.setBorder(new LineBorder(Color.BLACK));
        panelBL.setLayout(null);
        panelBL.setBounds(5, 100, 100, 450);
        panelBL.add(btli);
        panelBL.add(btp);
        panelBL.add(btt);
        panelBL.add(btlo);

        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.WHITE));
        panel.setBackground(Color.BLACK);
        cardLayout = new CardLayout();
        panel.setLayout(cardLayout);
        panel.setBounds(120, 100, 550, 450);


        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.BLACK);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);

        JLabel vl = new JLabel("Masukkan PIN / Kode Verifikasi:");
        vl.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 4;
        formPanel.add(vl, gbc);

        vtf = new JTextField(20);
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(vtf, gbc);

        btv = new JButton("Vertifikasi");
        gbc.gridx = 2;
        gbc.gridy = 6;
        formPanel.add(btv, gbc);



        JLabel wcm = new JLabel("Selamat Datang");
        wcm.setFont(new Font("San Francisco", Font.BOLD, 40));
        wcm.setBounds(110,4,500,100);
        wcm.setForeground(Color.black);
        JLabel wcmd = new JLabel("Aplikasi ini dibuat menggunakan JAVA dengan metode Autentikasi OAuth1.0");
        wcmd.setFont(new Font("San Francisco", Font.PLAIN, 15));
        wcmd.setForeground(Color.black);
        wcmd.setBounds(40,65,1000,100);

        panelp = new JPanel();
        panelp.setBackground(Color.WHITE);
        panelp.add(formPanel);
        panelp.setLayout(null);
        panelp.add(wcm);
        panelp.add(wcmd);
        panelp.add(btlp);
        panel.add(panelp, "Menu 0");

        panell = new JPanel();
        panell.setBackground(Color.BLACK);
        panell.add(formPanel, BorderLayout.CENTER);
        panel.add(panell, "Menu 1");


        panelup = new JPanel();
        panelup.setBackground(Color.WHITE);
        panelup.setLayout(new BorderLayout());
        uinp = new JTextArea();
        uinp.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(uinp);
        panelup.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelup, "Menu 2");

        JLabel pn = new JLabel("Post Tweet");
        pn.setFont(new Font("San Francisco", Font.BOLD, 30));
        pn.setBounds(110,4,500,100);
        pn.setForeground(Color.white);
        tweet = new JTextArea();
        tweet.setFont(new Font("San Francisco", Font.BOLD, 20));
        tweet.setBounds(110,100,300,50);


        badd = new JButton("+");
        badd.setFont(new Font("CALIBRE", Font.BOLD, 30));
        badd.setForeground(Color.WHITE);
        badd.setBackground(Color.WHITE);
        badd.setOpaque(false);
        badd.setBorder(new LineBorder(Color.BLACK));
        badd.setBorderPainted(true);
        badd.setBounds(420, 100, 50, 50);

        bsend = new JButton("Kirim");
        bsend.setFont(new Font("CALIBRE", Font.BOLD, 30));
        bsend.setForeground(Color.WHITE);
        bsend.setBackground(Color.WHITE);
        bsend.setOpaque(false);
        bsend.setBorder(new LineBorder(Color.BLACK));
        bsend.setBorderPainted(true);
        bsend.setBounds(200, 150, 100, 50);


        JLabel dl = new JLabel("Hapus Tweet");
        dl.setFont(new Font("San Francisco", Font.BOLD, 30));
        dl.setBounds(110,220,500,100);
        dl.setForeground(Color.white);
        del = new JTextArea();
        del.setFont(new Font("San Francisco", Font.BOLD, 20));
        del.setBounds(110,310,300,50);

        bdel = new JButton("Hapus");
        bdel.setFont(new Font("CALIBRE", Font.BOLD, 30));
        bdel.setForeground(Color.WHITE);
        bdel.setBackground(Color.WHITE);
        bdel.setOpaque(false);
        bdel.setBorder(new LineBorder(Color.BLACK));
        bdel.setBorderPainted(true);
        bdel.setBounds(200, 360, 100, 50);

        panelt = new JPanel();
        panelt.setBackground(Color.BLACK);
        panelt.setLayout(null);
        panelt.add(pn);
        panelt.add(badd);
        panelt.add(bsend);
        panelt.add(dl);
        panelt.add(del);
        panelt.add(bdel);
        panelt.add(tweet);

        panel.add(panelt, "Menu 3");

        panelt = new JPanel();
        panelt.setBackground(Color.WHITE);
        panel.add(panelt, "Menu 4");

        frame.add(panelBL);
        frame.add(panel);
        frame.add(jdl);
        frame.setVisible(true);

        // Action listeners
        btli.addActionListener(this);
        btlp.addActionListener(this);
        btp.addActionListener(this);
        btt.addActionListener(this);
        btlo.addActionListener(this);
        btv.addActionListener(this);
        badd.addActionListener(this);
        bdel.addActionListener(this);
        bsend.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btli) {
                System.out.println("Login");
                cardLayout.show(panel, "Menu 0");
            } else if (e.getSource() == btlp) {
                cardLayout.show(panel, "Menu 1");
                String authorizationUrl = HttpConn.login();
                this.requestToken = authorizationUrl.split("=")[1]; // Extract the request token from the URL
                JOptionPane.showMessageDialog(frame, "Buka url untuk mengizinkan akun anda menggunakkan aplikasi ini:\n" + authorizationUrl, "Authorization URL", JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource() == btv) {
                String verifier = vtf.getText().trim();
                if (!verifier.isEmpty()) {
                    String accessToken = OAuth.getAccessToken(requestToken, verifier);
                    HttpConn.ACCESS_TOKEN = accessToken.split("&")[0].split("=")[1];
                    HttpConn.ACCESS_TOKEN_SECRET = accessToken.split("&")[1].split("=")[1];
                    JOptionPane.showMessageDialog(frame, "Berhasil Login.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("Login Sukses");
                } else {
                    JOptionPane.showMessageDialog(frame, "Tolong Masukkan Kode Verifikasi.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btp) {
                String userDetails = HttpConn.getUserDetails();
                uinp.setText(userDetails);
                cardLayout.show(panel, "Menu 2");
            } else if (e.getSource() == btt) {
                System.out.println("Tweet");
                cardLayout.show(panel, "Menu 3");
            } else if (e.getSource() == badd) {
                System.out.println("add");
                JFileChooser add = new JFileChooser();
                int response = add.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION){
                    File file = new File(add.getSelectedFile().getAbsolutePath());
                    System.out.println(file);
                }else {
                }
            } else if (e.getSource() == bsend) {
                String tweetText = tweet.getText().trim();
                if (!tweetText.isEmpty()) {
                    try {
                        String jsonBody = "{\"text\":\"" + tweetText + "\"}";
                        HttpConn.sendTweet(jsonBody);
                        JOptionPane.showMessageDialog(frame, "Tweter Berhasil di Post.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Gagal mengirim: " + ex.getMessage(), "Gagal", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Masukkan Tweet.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == bdel) {
                String verifier = del.getText().trim();
                String tweetIdToDelete = del.getText();
                if (!verifier.isEmpty()) {
                    HttpConn.deleteTweet(tweetIdToDelete);
                    JOptionPane.showMessageDialog(frame, "Berhasil Menghapus Post.", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Masukkan ID Post Tweet.", "Gagal", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == btlo) {
                HttpConn.logout();
                JOptionPane.showMessageDialog(frame, "Berhasil Logout.", "Logout", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(panel, "Menu 0");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error!!: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new MainX();
    }
}
