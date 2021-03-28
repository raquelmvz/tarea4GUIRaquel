/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author raquel
 */
public class PanelPrincipal extends JPanel {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;
    private String operando;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        ArrayList<String> operandos = new ArrayList<>();
        operando = "";

        for (JButton boton : this.botonera.getgrupoBotones()) {

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    //Objeto que se acciona
                    Object obj = ae.getSource();

                    //el objeto es un boton
                    if (obj instanceof JButton) {

//                        if (((JButton) obj).getText() != "+") {
//                            operando += ((JButton) obj).getText();
//                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
//                        }
//
//                        System.out.println("operando:  " + operando);
                        String contenidoBoton = ((JButton) obj).getText();
                        //System.out.println("contenido -- " + contenidoBoton);

                        if (contenidoBoton == "+") {
                            //System.out.println("operando --" + operando);
                            operandos.add(operando);
                            //System.out.println("lsita -- " + operandos.toString());
                            operando = "";
                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                        } else if (contenidoBoton == "=") {
                            //System.out.println("lsita op --" + operandos.toString());
                            operandos.add(operando);

                            double resultadoSuma = 0;
                            //System.out.println(resultadoSuma);
                            for (String op : operandos) {

                                resultadoSuma += Double.parseDouble(op);
                                //System.out.println(resultadoSuma);

                            }

                            String resultadoString = String.valueOf(resultadoSuma);

                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText() + resultadoString);
                        } else {
                            //System.out.println("operando antes -" + operando);
                            operando += contenidoBoton;
                            //System.out.println("operando despues - " + operando);
                            //System.out.println("lsita -- " + operandos.toString());
                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                        }
                    }
                }
            });

        }

    }

}
