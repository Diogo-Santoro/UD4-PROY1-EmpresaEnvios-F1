/**
 * Un envío agrupa los paquetes que un cliente manda a un mismo destino.
 * Un envío admite como máximo tres paquetes.
 *
 * Ninguno de los métodos de esta clase escribe en pantalla.
 *
 * @author
 */
public class Envio {

    /**
     * private pues solo la clase Envio puede usarla,
     * static pues pertenece solo a clase,
     * final pues es una constante.
     */
    private static final int MAX_PAQUETES = 3;
        private String codigo_seguimiento;
        private String codigo_postal;
        private Paquete paquete1;
        private Paquete paquete2;
        private Paquete paquete3;
    /**
     * Crea un envío sin paquetes.
     *
     * @param codigo       código de seguimiento, p.ej. "PX00000001R"
     * @param codigoPostal código postal del destino, p.ej. "36202"
     */
    public Envio(String codigo, String codigoPostal) {
        this.codigo_seguimiento = codigo;
        this.codigo_postal = codigoPostal;
    }

    public String getCodigo() {
        return this.codigo_seguimiento;
    }

    public String getCodigoPostal() {
        return this.codigo_postal;
    }

    /**
     * @return cuántos paquetes tiene el envío (0 a 3)
     * Para mejorar el algoritmo: Los paquetes se añaden siempre en orden, así que nunca hay huecos entre ellos.
     */
    public int getNumeroPaquetes() {
        int countPaquetes = 0;
        if (this.paquete1 != null) {
            countPaquetes++;
        }

        if (this.paquete2 != null) {
            countPaquetes++;
        }

        if (this.paquete3 != null) {
            countPaquetes++;
        }

        return countPaquetes;
    }

    /**
     * @return true si ya no se pueden añadir más paquetes
     */
    public boolean estaCompleto() {
        return this.getNumeroPaquetes() == MAX_PAQUETES;
    }

    /**
     * Añade un paquete al envío si no está completo.
     *
     * @return true si se ha añadido, false si el envío estaba completo
     */
    public boolean addPaquete(Paquete paquete) {
        if (estaCompleto()) {
            return false;
        }

        if (this.paquete1 == null) {
                this.paquete1 = paquete;
        }
        
        else if (this.paquete2 == null) {
                this.paquete2 = paquete;
        }
        
        else {
            this.paquete3 = paquete;
        }
        
        return true;
    }

    /**
     * Crea un paquete con las medidas y peso indicados y lo añade al envío.
     *
     * @return true si se ha añadido, false si el envío estaba completo
     */
    public boolean addPaquete(double alto, double ancho, double largo, double peso) {
        Paquete paquete = new Paquete(alto, ancho, largo, peso);
        return this.addPaquete(paquete);
    }

    /**
     * @return la suma de los pesos reales de los paquetes (kg)
     */
    public double calcularPesoReal() {
        double valorPaquete1 = 0;
        double valorPaquete2 = 0;
        double valorPaquete3 = 0;

        if (this.paquete1 != null) {
            valorPaquete1 = this.paquete1.getPeso();
        }
        if (this.paquete2 != null) {
            valorPaquete2 = this.paquete2.getPeso();
        }
        if (this.paquete3 != null) {
            valorPaquete3 = this.paquete3.getPeso();
        }

        return valorPaquete1 + valorPaquete2 + valorPaquete3;
    }

    /**
     * Suma los pesos facturables de los paquetes y redondea hacia arriba
     * al kilo entero.
     *
     * @return el peso facturable del envío (kg enteros)
     */
    public double calcularPesoFacturable() {
        double valorTotal = 0;
        if (this.paquete1 != null) {
            valorTotal += this.paquete1.calcularPesoFacturable();
        }
        if (this.paquete2 != null) {
            valorTotal += this.paquete2.calcularPesoFacturable();
        }
        if (this.paquete3 != null) {
            valorTotal += this.paquete3.calcularPesoFacturable();
        }

        return Math.ceil(valorTotal);
    }

    /**
     * @return el paquete de mayor peso real; si hay empate, el que se añadió
     * antes; null si el envío no tiene paquetes
     */
    public Paquete getPaqueteMasPesado() {
        double valorPaquete1 = 0;
        double valorPaquete2 = 0;
        double valorPaquete3 = 0;

        if (this.paquete1 == null && this.paquete2 == null && this.paquete3 == null) {
            return null;
        }

        if (this.paquete1 != null) {
            valorPaquete1 = this.paquete1.getPeso();
        }
        if (this.paquete2 != null) {
            valorPaquete2 = this.paquete2.getPeso();
        }
        if (this.paquete3 != null) {
            valorPaquete3 = this.paquete3.getPeso();
        }

        if (valorPaquete1 >= valorPaquete2 && valorPaquete1 >= valorPaquete3) {
            return this.paquete1;
        } else if (valorPaquete2 >= valorPaquete1 && valorPaquete2 >= valorPaquete3) {
            return this.paquete2;
        } else {
            return this.paquete3;
        }
    }

    /**
     * @return representación textual del envío (ver enunciado)
     */
    public String toString() {;
        String stringRetorno = "Envío " + this.getCodigo() + " -> CP " + this.getCodigoPostal() + " (" + this.getNumeroPaquetes() + " paquete/s)\n";
        if (this.paquete1 != null) {
            stringRetorno += "  1. " + paquete1.toString() + "\n";
        }
        if (this.paquete2 != null) {
            stringRetorno += "  2. " + paquete2.toString() + "\n";
        }
        if (this.paquete3 != null) {
            stringRetorno += "  3. " + paquete3.toString() + "\n";
        }

        stringRetorno += "Peso real: " + this.calcularPesoReal() + " kg | Peso facturable: " + this.calcularPesoFacturable() + " kg";

        return stringRetorno;
    }
}
