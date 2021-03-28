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
    private ArrayList<String> operandos;
    //private String resultadoOperacion;

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

        operandos = new ArrayList<>();

        operando = "";

        for (JButton boton : this.botonera.getgrupoBotones()) {

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {

                    //Objeto que se acciona
                    Object obj = ae.getSource();

                    //el objeto es un boton
                    if (obj instanceof JButton) {

                        String contenidoBoton = ((JButton) obj).getText();

                        switch (contenidoBoton) {
                            case "+":
                                operandos.add(operando);
                                operando = "";
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                tipoOperacion = 1;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                                break;

                            case "-":
                                operandos.add(operando);
                                operando = "";
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                tipoOperacion = 2;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                                break;

                            case "=":
                                operandos.add(operando);
                                operando = "";
                                String solucion = actualizarCalculos(tipoOperacion);
                                //limpio la lista de operandos
                                operandos.clear();

                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText() + solucion);
                                break;
                            default:
                                operando += contenidoBoton;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                            //areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                        }
                        /*if (contenidoBoton == "+") {
                            operandos.add(operando);
                            operando = "";
                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                        } else if (contenidoBoton == "=") {
                            operandos.add(operando);

                            double resultadoSuma = 0;
                            for (String op : operandos) {

                                resultadoSuma += Double.parseDouble(op);

                            }
                            operandos.clear();

                            String resultadoString = String.valueOf(resultadoSuma);

                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText() + resultadoString);
                        } else {
                            operando += contenidoBoton;
                            areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());
                        }*/
                    }
                }
            });

        }

    }

    //Metodo para realizar la operacion que toque
    public String actualizarCalculos(int tipo) {

        switch (tipo) {
            //tipo 1 es la suma
            case 1:

                double resultadoSuma = 0;

                //sumo los operandos hasta el momento
                resultadoSuma = Double.parseDouble(operandos.get(0)) + Double.parseDouble(operandos.get(1));
                //elimino los operandos de la lista
                operandos.clear();
                //añado como operando el resultado de la operacion
                operandos.add(String.valueOf(resultadoSuma));
                return String.valueOf(resultadoSuma);

            //tipo 2 es la resta
            case 2:

                double resultadoResta = 0;

                //resto los operandos hasta el momento
                resultadoResta = Double.parseDouble(operandos.get(0)) - Double.parseDouble(operandos.get(1));

                //elimino los operandos de la lista
                operandos.clear();
                //añado como operando el resultado de la operacion
                operandos.add(String.valueOf(resultadoResta));
                return String.valueOf(resultadoResta);

            default:
                return "0";

            //case 2:
        }

    }

}
