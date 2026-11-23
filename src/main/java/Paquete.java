/**
 * Un bulto físico que forma parte de un envío.
 *
 * @author
 */
public class Paquete {

    /** cm3 que equivalen a 1 kg a efectos de facturación */
    public static final double FACTOR_VOLUMETRICO = 5000;

    private Dimension dimension;
    private double peso;

    /**
     * Crea un paquete a partir de una dimensión ya creada y su peso real (kg).
     */
    public Paquete(Dimension dimension, double peso) {
        inicializar(dimension, peso);
    }

    /**
     * Crea un paquete a partir de sus medidas (cm) y su peso real (kg).
     * Sin duplicar el código del otro constructor.
     */
    public Paquete(double alto, double ancho, double largo, double peso) {
        Dimension dimension = new Dimension(alto, ancho, largo);
        inicializar(dimension, peso);
    }

    private void inicializar(Dimension dimension, double peso) {
        this.dimension = dimension;
        this.peso = peso;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    /**
     * @return el peso real en kg
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * @return el volumen del paquete en cm3
     */
    public double calcularVolumen() {
        return this.dimension.calcularVolumen();
    }

    /**
     * @return el peso volumétrico en kg (volumen / FACTOR_VOLUMETRICO)
     */
    public double calcularPesoVolumetrico() {
        return this.calcularVolumen() / FACTOR_VOLUMETRICO;
    }

    /**
     * El peso facturable es el mayor entre el peso real y el volumétrico.
     *
     * @return el peso facturable en kg
     */
    public double calcularPesoFacturable() {
        if (this.peso > this.calcularPesoVolumetrico()) {
            return this.peso;
        } else {
            return this.calcularPesoVolumetrico();
        }
    }

    /**
     * @return por ejemplo "40.0 x 30.0 x 50.0 cm | 8.0 kg (facturable 12.0 kg)"
     */
    public String toString() {
        return this.dimension.getAncho() + " (Ancho) X " +
         this.dimension.getLargo() + " (Largo) X " +
          this.dimension.getAlto() + " (Alto) X | " +
           this.peso + " kg (facturable: " +
            this.calcularPesoFacturable() + " kg)." ;
    }
}
