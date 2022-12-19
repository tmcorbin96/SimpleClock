//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;
    JToggleButton formatButton;
    JToggleButton switchButton;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(500, 320);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat=new SimpleDateFormat("EEEE");
        dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.ITALIC, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.PINK);
        timeLabel.setOpaque(true);
        dayLabel=new JLabel();
        dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));

        dateLabel=new JLabel();
        dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

        formatButton=new JToggleButton("Change12/24 hr format");
        formatButton.setBounds(130,100,100, 40);//x axis, y axis, width, height
        formatButton.addActionListener(objSwitchTimeFormat);

        switchButton=new JToggleButton("Change EST/GMT");
        switchButton.setBounds(130,100,100, 40);
        switchButton.addActionListener(objSwitchTimeZone);

        this.add(dayLabel);
        this.add(dateLabel);
        this.add(timeLabel);
        this.add(formatButton);
        this.add(switchButton);
        this.setVisible(true);

        //setTimer();
        run();
    }
    ActionListener objSwitchTimeZone=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(switchButton.isSelected()) timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            else timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
            timeLabel.setText(timeFormat.format(Calendar.getInstance().getTime()));
            dayLabel.setText( dayFormat.format(Calendar.getInstance().getTime()));
            dateLabel.setText(dateFormat.format(Calendar.getInstance().getTime()));
        }
    };
    ActionListener objSwitchTimeFormat=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(formatButton.isSelected()){
                timeFormat = new SimpleDateFormat(" hh:mm:ss a ");
            }
            else {
                timeFormat = new SimpleDateFormat(" HH:mm:ss ");
            }
            timeLabel.setText(timeFormat.format(Calendar.getInstance().getTime()));
        }
    };

    @Override
    public void run(){
        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);

        day = dayFormat.format(Calendar.getInstance().getTime());
        dayLabel.setText(day);

        date = dateFormat.format(Calendar.getInstance().getTime());
        dateLabel.setText(date);
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {run();}).start();

    }
    public static void main(String[] args) {
        new SimpleClock();
    }
}