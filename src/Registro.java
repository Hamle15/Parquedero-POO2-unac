import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Registro {
    private JRadioButton MotoRa;
    private JRadioButton CarroRa;
    public JPanel jRegistro;
    private JTextField textPlaca;
    private JLabel Marca;
    private JTextField textMarca;
    private JTextField textModelo;
    private JTextField textFecha;
    private JTextField textHora;
    private JButton Cacular;
    private JTextField textHoraSal;
    private JTextField textFechasalida;


    private double numeros = 0;
    private String estado;
    private float tarifa = 5000;


    public Registro() {
        //radio botones
        MotoRa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == MotoRa){
                    System.out.println("Moto");

                }


            }
        });
        CarroRa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == CarroRa) {
                    System.out.println("Carro");
                }
            }
        });

        //modelo
        textModelo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!validarString(textModelo.getText())){
                    JOptionPane.showMessageDialog(new JFrame(), "Solo texto");
                }
            }
        });
        textFecha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (!isValidFormat(textFecha.getText())) ;
            }
        });


        textMarca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!validarString(textMarca.getText())){
                    JOptionPane.showMessageDialog(new JFrame(),"Error no valen numeros");
                }
            }
        });

        textPlaca.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!validarPlaca(textPlaca.getText())){
                    JOptionPane.showMessageDialog(new Frame(),"Error coloque una placa real");
                }
            }
        });
        textHora.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!validTime(textHora.getText()));
            }
        });
        textHoraSal.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(!validTime(textHoraSal.getText()));
            }
        });

        Cacular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double sumatoria = 0;
                if(!textPlaca.getText().isEmpty()&&!textMarca.getText().isEmpty()&&!textModelo.getText().isEmpty()&&
                !textFecha.getText().isEmpty()&&!textHora.getText().isEmpty()&&!textFechasalida.getText().isEmpty()&&
                !textHoraSal.getText().isEmpty()&&MotoRa.isSelected()||CarroRa.isSelected()){
                    try{
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        Date firstHoru = format.parse(textHora.getText());
                        Date secondHoru = format.parse(textHoraSal.getText());
                        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date firstDate = format2.parse(textFecha.getText());
                        Date secondDate = format2.parse(textFechasalida.getText());

                        if(firstHoru.getTime() >= secondHoru.getTime()){
                            long timeDiff = Math.abs(secondHoru.getTime() - firstHoru.getTime());
                            long days = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);




                            long timeDiff2 = Math.abs(secondDate.getTime()-firstDate.getTime());
                            long days2 = TimeUnit.HOURS.convert(timeDiff2, TimeUnit.MILLISECONDS);


                            sumatoria=days2-days;
                            double pagar = sumatoria * tarifa;
                            JOptionPane.showMessageDialog(new JFrame(), "Tiempo en total que estuvo : " + sumatoria +
                                    "Y lo que debe pagar es: "  + pagar);


                        }else {
                            long timeDiff = Math.abs(secondHoru.getTime() - firstHoru.getTime());
                            long days = TimeUnit.HOURS.convert(timeDiff, TimeUnit.MILLISECONDS);




                            long timeDiff2 = Math.abs(secondDate.getTime()-firstDate.getTime());
                            long days2 = TimeUnit.HOURS.convert(timeDiff2, TimeUnit.MILLISECONDS);

                            sumatoria=days+days2;
                            double pagar = sumatoria * tarifa;
                            JOptionPane.showMessageDialog(new JFrame(),"Tiempo en total que estuvo : " + sumatoria +
                                    "Y lo que debe pagar es: " +  pagar);

                        }

                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(new JFrame(),"Error" +ex.getMessage());
                    }

                }else {
                    JOptionPane.showMessageDialog(new JFrame(),"Llene todos los campos");
                }
            }
        });


        textFechasalida.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(isValidFormat(textFechasalida.getText()));
            }
        });
    }
    private static boolean validTime(String time) {
        Date myTime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            myTime = format.parse(time);
            if (!time.equals(format.format(myTime))) {
                myTime = null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
        }
        if (myTime == null) {
            return false;
        } else {
            return true;
        }
    }



    private static boolean isValidFormat(String date) {
        Date myDate = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            myDate = format.parse(date);
            if (!date.equals(format.format(myDate))) {
                myDate = null;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage());
        }
        if (myDate == null) {
            return false;
        } else {
            return true;
        }
    }
    private  static  boolean validarPlaca(String placa){
        return placa.matches("^([A-Za-z]{3}[0-9]{2}[A-za-z0-9]{1})$");
    }

    public  static  boolean validarString(String nombre){

        return nombre.matches("^([A-Z]{1}[a-z]+[ ]*){1,2}$");
    }
    private static Date getDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:MM/ss");
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }







}



