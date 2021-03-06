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
                                //al accionar un operador el numero que se haya formado
                                //hasta el momento se añade a una lista de operandos
                                operandos.add(operando);
                                //se borra el contenido de operando para formar uno nuevo
                                operando = "";
                                //si se ha establecido ya un tipo de operacion antes se actualizan los resultados
                                //(esto es para ir haciendo los calculos de dos en dos por orden
                                //por si queremos por ej sumar dos operandos y luego restarle otro
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                //el num 1 indicara que se trata de una suma en el metodo actualizarCalculos
                                tipoOperacion = 1;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                                //para que no se puedan clicar otros botones de operaciones
                                desactivarOperadores();
                                break;

                            case "-":
                                operandos.add(operando);
                                operando = "";
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                //el num 2 indicara que se trata de una resta en el metodo actualizarCalculos
                                tipoOperacion = 2;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                                //para que no se puedan clicar otros botones de operaciones
                                desactivarOperadores();
                                break;

                            case "*":
                                operandos.add(operando);
                                operando = "";
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                //el num 3 indicara que se trata de una multiplicacion en el metodo actualizarCalculos
                                tipoOperacion = 3;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                                //para que no se puedan clicar otros botones de operaciones
                                desactivarOperadores();
                                break;

                            case "/":
                                operandos.add(operando);
                                operando = "";
                                if (tipoOperacion != -1) {
                                    actualizarCalculos(tipoOperacion);

                                }
                                //el num 4 indicara que se trata de una division en el metodo actualizarCalculos
                                tipoOperacion = 4;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                                //para que no se puedan clicar otros botones de operaciones
                                desactivarOperadores();
                                break;

                            case "C":

                                //borra todo y lo pone a 0
                                operando = "";
                                operandos.clear();
                                tipoOperacion = -1;
                                areaTexto.setText("");
                                break;

                            case "=":
                                operandos.add(operando);
                                operando = "";
                                actualizarCalculos(tipoOperacion);
                                tipoOperacion = -1;

                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText() + operandos.get(0));

                                break;

                            default:
                                //mientras los botones que se clican sean digitos se van concatenando 
                                //para formar un operando
                                operando += contenidoBoton;
                                areaTexto.setText(areaTexto.getText() + ((JButton) obj).getText());

                                //para volver a activar los botones
                                activarOperadores();
                                break;

                        }

                    }
                }
            });

        }

    }

    //Metodo para realizar la operacion que toque
    //se pasa como parametro un numero que identifica el tipo de operacion a realizar
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

            case 3:

                double resultadoMulti = 0;

                //multiplico los operandos hasta el momento
                resultadoMulti = Double.parseDouble(operandos.get(0)) * Double.parseDouble(operandos.get(1));

                //elimino los operandos de la lista
                operandos.clear();
                //añado como operando el resultado de la operacion
                operandos.add(String.valueOf(resultadoMulti));
                return String.valueOf(resultadoMulti);

            case 4:

                double resultadoDiv = 0;

                //divido los operandos hasta el momento
                resultadoDiv = Double.parseDouble(operandos.get(0)) / Double.parseDouble(operandos.get(1));

                //elimino los operandos de la lista
                operandos.clear();
                //añado como operando el resultado de la operacion
                operandos.add(String.valueOf(resultadoDiv));
                return String.valueOf(resultadoDiv);

            default:
                return "0";

        }

    }

    public void desactivarOperadores() {
        botonera.grupoBotones[10].setEnabled(false);
        botonera.grupoBotones[11].setEnabled(false);
        botonera.grupoBotones[12].setEnabled(false);
        botonera.grupoBotones[13].setEnabled(false);
        botonera.grupoBotones[14].setEnabled(false);
    }

    public void activarOperadores() {
        botonera.grupoBotones[10].setEnabled(true);
        botonera.grupoBotones[11].setEnabled(true);
        botonera.grupoBotones[12].setEnabled(true);
        botonera.grupoBotones[13].setEnabled(true);
        botonera.grupoBotones[14].setEnabled(true);
    }

}
