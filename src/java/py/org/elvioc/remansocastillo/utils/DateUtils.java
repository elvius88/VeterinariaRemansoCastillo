package py.org.elvioc.remansocastillo.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que implementa operaciones de manipulación de fechas
 *
 * @author Elvio Teófilo Contreras Martinez <elvicontreras02@gmail.com>
 * @date Nov 15, 2017
 * @time 3:29:39 PM
 *
 */
public class DateUtils {

    /**
     * Retorna un hash con los meses del año
     *
     * @return el hashMap de los meses del año
     */
    public static Map<String, String> getMeses() {
        Map mesesHM = new LinkedHashMap();
        mesesHM.put(1, "Enero");
        mesesHM.put(2, "Febrero");
        mesesHM.put(3, "Marzo");
        mesesHM.put(4, "Abril");
        mesesHM.put(5, "Mayo");
        mesesHM.put(6, "Junio");
        mesesHM.put(7, "Julio");
        mesesHM.put(8, "Agosto");
        mesesHM.put(9, "Septiembre");
        mesesHM.put(10, "Octubre");
        mesesHM.put(11, "Noviembre");
        mesesHM.put(12, "Diciembre");
        return mesesHM;
    }

    /**
     * Retorna el mes de la fecha dada como String
     *
     * @param fecha
     * @return
     */
    public static String getMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        Map<Integer, String> mesesHM = new ConcurrentHashMap<>();
        mesesHM.put(1, "Enero");
        mesesHM.put(2, "Febrero");
        mesesHM.put(3, "Marzo");
        mesesHM.put(4, "Abril");
        mesesHM.put(5, "Mayo");
        mesesHM.put(6, "Junio");
        mesesHM.put(7, "Julio");
        mesesHM.put(8, "Agosto");
        mesesHM.put(9, "Septiembre");
        mesesHM.put(10, "Octubre");
        mesesHM.put(11, "Noviembre");
        mesesHM.put(12, "Diciembre");
        return mesesHM.get(cal.get(Calendar.MONTH) + 1);
    }

    /**
     * Retorna el año de la fecha dada
     *
     * @param fecha
     * @return
     */
    public static int getAnho(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.YEAR);
    }

    /**
     * Convierte la fecha al formato requerido
     *
     * @param fecha la fecha a convertir
     * @param formatoCovertir el formato a convertir
     * @return Un string con el formato pasado por parámetro
     */
    public static String convertirFecha(Date fecha, String formatoCovertir) {
        SimpleDateFormat formato = new SimpleDateFormat(formatoCovertir);
        return formato.format(fecha);
    }

    /**
     * Convierte la fecha al formato yyyyMMdd
     *
     * @param fecha la fecha a convertir
     * @return Un string con el formato yyyyMMdd
     */
    public static String convertirFechaAAAAMMDD(Date fecha) {
        String fechaFormateada = String.valueOf(fecha.getYear() + 1900) + "" + String.valueOf(fecha.getMonth() + 1) + "" + String.valueOf(fecha.getDate());
        return fechaFormateada;
    }

    /**
     * *
     * Retorna el año actual
     *
     * @return
     */
    public static Integer getAnhoActual() {
        return (new Date().getYear() + 1900);
    }

    /**
     * Retorna el mes actual
     *
     * @return
     */
    public static Integer getMesActual() {
        return (new Date().getMonth() + 1);
    }

    /**
     * Retorna la fecha que resulta de sumarle una cantidad de dias dado
     *
     * @param fecha
     * @param dias
     * @return
     */
    public static Date sumarDiasFecha(Date fecha, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.DATE, dias);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Retorna la fecha que resulta de restarle una cantidad de dias dado
     *
     * @param fecha
     * @param dias
     * @return
     */
    public static Date restarDiasFecha(Date fecha, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fecha.getTime());
        cal.add(Calendar.DATE, -dias);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Retorna el primer día del mes de la fecha dada
     *
     * @param fecha
     * @return
     */
    public static Date getPrimerDiaDelMes(Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        cal.set(Calendar.DAY_OF_MONTH, 01);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 00);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Retorna el último día del mes de la fecha dada
     *
     * @param fecha
     * @return
     */
    public static Date getUltimoDiaDelMes(Date fecha) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(fecha);
        int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 59);
        cal.set(Calendar.DATE, ultimoDia);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * Conversion de String a Date, formato soportado: 1. dd/MM/yyyy 2.
     * dd/MM/yyyy HH:mm:ss
     *
     * @param fechaString
     * @return
     */
    public static Date getFecha(String fechaString) {
        Date fecha = null;
        String formato, aux;
        DateFormat df;
        int dia, mes, anho, hora, min;
        try {
            //OBTENER VALORES
            if (fechaString.length() > 10) {
                formato = "dd/MM/yyyy HH:mm";
                dia = Integer.parseInt(fechaString.substring(0, 2));
                mes = Integer.parseInt(fechaString.substring(3, 5));
                anho = Integer.parseInt(fechaString.substring(6, 10));
                hora = Integer.parseInt(fechaString.substring(11, 13));
                min = Integer.parseInt(fechaString.substring(14, 16));
            } else if (fechaString.length() == 10) {
                formato = "dd/MM/yyyy";
                dia = Integer.parseInt(fechaString.substring(0, 2));
                mes = Integer.parseInt(fechaString.substring(3, 5));
                anho = Integer.parseInt(fechaString.substring(6, 10));
                hora = 0;
                min = 0;
            } else {
                return null;
            }
            aux = validarFecha(dia, mes, anho, hora, min);
            if (aux.equals("")) {
                df = new SimpleDateFormat(formato);
                fecha = new Date(df.parse(fechaString).getTime());
            }
        } catch (NumberFormatException | ParseException e) {
            fecha = null;
        }

        return fecha;
    }
    
    /**
     * Conviente a Date una fecha dada en String en un formato dado
     * 
     * @param fechaString
     * @param formato
     * @return 
     */
    public static Date getFecha(String fechaString, String formato) {
        if (fechaString != null && formato != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(formato);
            try {
                return formatter.parse(fechaString);
            } catch (ParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Se obtiene fecha dada en el formato aaaaMMdd
     *
     * @param fecha
     * @return Un string que representa a la fecha en el formato aaaaMMdd
     */
    public static Integer getFecha(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        String anho = String.valueOf(cal.get(Calendar.YEAR));
        String mes = ((cal.get(Calendar.MONTH) + 1) > 9) ? (cal.get(Calendar.MONTH) + 1) + "" : "0" + (cal.get(Calendar.MONTH) + 1);
        String dia = (cal.get(Calendar.DAY_OF_MONTH) > 9) ? cal.get(Calendar.DAY_OF_MONTH) + "" : "0" + (cal.get(Calendar.DAY_OF_MONTH));
        return Integer.parseInt(anho + mes + dia);
    }

    /**
     * Obtiene la hora en formato HHmmss
     *
     * @param fecha
     * @return fecha como string en formato HHmmss
     */
    public static Integer getHora(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        String hora = (cal.get(Calendar.HOUR_OF_DAY) > 9) ? cal.get(Calendar.HOUR_OF_DAY) + "" : "0" + (cal.get(Calendar.HOUR_OF_DAY));
        String minuto = (cal.get(Calendar.MINUTE) > 9) ? cal.get(Calendar.MINUTE) + "" : "0" + (cal.get(Calendar.MINUTE));
        String segundo = (cal.get(Calendar.SECOND) > 9) ? cal.get(Calendar.SECOND) + "" : "0" + (cal.get(Calendar.SECOND));
        return Integer.parseInt(hora + minuto + segundo);
    }

    /**
     * Retorna si la fecha dada es válida o no
     *
     * @param dia
     * @param mes
     * @param anho
     * @param hora
     * @param min
     * @return
     */
    public static String validarFecha(int dia, int mes, int anho, int hora, int min) {
        String resultado;
        int ultimoDia;

        resultado = validarAnho(anho);

        if (!resultado.equals("")) {
            return resultado;
        }

        if (resultado.equals("")) {
            ultimoDia = getDiasMes(anho, mes);
            if (ultimoDia != 0) {
                if (dia >= 1 && dia <= ultimoDia) {
                    resultado = validarHora(hora);

                    if (resultado.equals("")) {
                        resultado = validarMinuto(min);
                    }
                } else {
                    resultado = "El día debe estar en un rango entre 1 - " + ultimoDia + ", Para el año " + anho + " y mes " + mes;
                }
            } else {
                resultado = "Error al obtener el último día del mes";
            }
        }
        return resultado;
    }

    /**
     * Valida si el año es válido (no sea negativo)
     *
     * @param anho
     * @return
     */
    public static String validarAnho(int anho) {
        String resultado;
        if (anho >= 1) {
            resultado = "";
        } else {
            resultado = "Año incorrecto, valor deber ser mayor a cero (0)";
        }
        return resultado;
    }

    /**
     * Valida si el mes es válido (si se encuentra en el rango 1 - 12)
     *
     * @param mes
     * @return
     */
    public static String validarMes(int mes) {
        String resultado;
        if (mes >= 1 && mes <= 12) {
            resultado = "";
        } else {
            resultado = "Mes incorrecto, valor deber estar entre 01 y 12";
        }
        return resultado;
    }

    /**
     * Valida si la hora es válida (si se encuentra en el rango 0 - 23)
     *
     * @param hora
     * @return
     */
    public static String validarHora(int hora) {
        String resultado;
        if (hora >= 0 && hora <= 23) {
            resultado = "";
        } else {
            resultado = "Hora fuera de rango, valor deber estar entre 00 y 23";
        }
        return resultado;
    }

    /**
     * Valida si el minuto es válido (si se encuentra en el rango 0 - 59)
     *
     * @param minuto
     * @return
     */
    public static String validarMinuto(int minuto) {
        String resultado;
        if (minuto >= 0 && minuto <= 59) {
            resultado = "";
        } else {
            resultado = "Minuto fuera de rango, valor deber estar entre 00 y 23";
        }
        return resultado;
    }

    /**
     * Retorna el último día según el mes y el año dados por parámetro. También
     * se evaluan si el año es bisiesto en el caso que el mes que se envíe sea
     * febrero = 02
     *
     * @param pAnho
     * @param pMes
     * @return
     */
    public static int getDiasMes(int pAnho, int pMes) {
        int anho, mes;
        GregorianCalendar cal;
        try {
            anho = pAnho;
            mes = pMes;
            cal = new GregorianCalendar();

            if (anho <= 0 || !(mes >= 1 && mes <= 12)) {
                return 0;
            } else {
                switch (mes) {
                    case 1: //enero
                    case 3: //marzo
                    case 5: //mayo
                    case 7: //julio
                    case 8: //agosto
                    case 10: //octubre
                    case 12: //diciembre
                        return 31;
                    case 4: //abril
                    case 6: //junio
                    case 9: //septiembre
                    case 11: //noviembre
                        return 30;
                    case 2: //febrero
                        if (cal.isLeapYear(anho)) {//AÑO BISIESTO
                            return 29;
                        } else { //AÑO NORMAL
                            return 28;
                        }
                    default:
                        return 0;
                }

            }
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Se obtiene la cantidad de dias que ya transcurrieron en el año de una
     * fecha determinada
     *
     * @param fecha
     * @return los días transcurridos
     */
    public static int getDiaDelAnho(Date fecha) {
        Integer diasAnho = 0;
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(fecha);
            GregorianCalendar cal2 = new GregorianCalendar();
            cal2.setTime(fecha);
            cal2.set(GregorianCalendar.DAY_OF_MONTH, 1);
            cal2.set(GregorianCalendar.MONTH, 0);
            Long diaFecha = (cal.getTimeInMillis() - cal2.getTimeInMillis()) / (1000 * 60 * 60 * 24);
            diasAnho = diaFecha.intValue();

            return diasAnho;
        } catch (Exception e) {
            return diasAnho;
        }
    }

    /**
     * Se obtiene un string con la fecha en formato ddMMyyyy
     *
     * @param fecha
     * @return
     */
    public static String getFechaString(Date fecha) {
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        return df.format(fecha);
    }

    /**
     * Se obtiene un string con la hora en formato hh:mm
     *
     * @param fecha
     * @return Un string con el formato de hora hh:mm
     */
    public static String getHoraString(Date fecha) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm");
        return df.format(fecha);
    }

    /**
     * Retorna la fecha en formato Timestamp
     *
     * @return Un timestamp
     */
    public static Timestamp getTimestamp() {
        Date fecha = new Date();
        long milisegundos = fecha.getTime();
        return new Timestamp(milisegundos);
    }

    public static void main(String[] args) {
        Date fecha = new Date();
        Map meses = DateUtils.getMeses();
        System.out.println(fecha.getDate() + "-" + meses.get(fecha.getMonth() + 1));
        System.out.println("Date: " + DateUtils.convertirFechaAAAAMMDD(fecha));
        System.out.println("Año Actual: " + DateUtils.getAnhoActual());
        System.out.println("Año: " + DateUtils.getAnho(fecha));
        System.out.println("Mes Actual: " + DateUtils.getMesActual());
        System.out.println("Mes: " + DateUtils.getMes(DateUtils.sumarDiasFecha(fecha, 50)));
        System.out.println("Sumar Dias: " + DateUtils.sumarDiasFecha(fecha, 10));
        System.out.println("Restar Dias: " + DateUtils.restarDiasFecha(fecha, 20));
        System.out.println("Primer Dia del mes: " + DateUtils.getPrimerDiaDelMes(fecha));
        System.out.println("Ultimo Dia del mes: " + DateUtils.getUltimoDiaDelMes(fecha));
        System.out.println("Convertir aaaaMMdd: " + DateUtils.convertirFechaAAAAMMDD(fecha));
        System.out.println("Convertir: " + DateUtils.convertirFecha(fecha,"dd/MM/yyyy HH:mm:ss"));
        System.out.println("Dias en el año ya transcurridos: " + DateUtils.getDiaDelAnho(fecha));
        System.out.println("Dias en un mes X de un año X: " + DateUtils.getDiasMes(fecha.getYear()-3,fecha.getMonth() + 1));
        System.out.println("Fecha a partir de un formato de fecha en String: " + DateUtils.getFecha("09/12/2016"));
        System.out.println("Fecha a partir de un formato de fecha en String y una fecha en String: " + DateUtils.getFecha("09/12/2016","dd-MM-yyyy"));
        System.out.println("Fecha como INT: " + DateUtils.getFecha(fecha));
        System.out.println("Fecha como String ddMMyyyy: " + DateUtils.getFechaString(fecha));
        System.out.println("Hora como String HH:ss: " + DateUtils.getHoraString(fecha));
        System.out.println("Meses: " + DateUtils.getMeses());
        System.out.println("Fecha como Timestamp: " + DateUtils.getTimestamp());
        System.out.println("Validacion de Fecha (Fallido): " + DateUtils.validarFecha(29, 2, 2014, 22, 30));
        System.out.println("Validacion de Fecha (Exitoso): " + DateUtils.validarFecha(29, 2, 2016, 22, 30));
    }

}
