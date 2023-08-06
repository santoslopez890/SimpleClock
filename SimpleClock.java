//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {
    

        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
        JToggleButton twelveTo24=new JToggleButton("Change to Military Time");
        JToggleButton gmtest=new JToggleButton("Change to gmt");
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(400, 400);
            this.setResizable(true);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.YELLOW);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            twelveTo24.addItemListener(changeTwelveToTwentyFourAndBack);
            twelveTo24.setFont(new Font("Ink Free",Font.BOLD,30));
            twelveTo24.setForeground(Color.BLUE);



            gmtest.addItemListener(changeToGmtAndBack);
            gmtest.setFont(new Font("Ink Free",Font.BOLD,30));
            gmtest.setForeground(Color.BLUE);
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(twelveTo24);
            this.add(gmtest);
            this.setVisible(true);
    
            setTimer();
        }

    ItemListener changeTwelveToTwentyFourAndBack = new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
            int stateToChange = event.getStateChange();
            if (stateToChange == event.SELECTED) {
                twelveTo24.setText("Change to Standard time");
                timeFormat = new SimpleDateFormat("  HH:mm:ss a  ");
            } else {
                twelveTo24.setText("Change to Military Time");
                timeFormat = new SimpleDateFormat("  hh:mm:ss a  ");
            }
        }
    };

    ItemListener changeToGmtAndBack = new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
            int stateToChange = event.getStateChange();
            if(stateToChange == event.SELECTED){
                gmtest.setText("Change to EST");
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            } else {
                gmtest.setText("Change to GMT");
                timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
            }

        }
    };
        public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
