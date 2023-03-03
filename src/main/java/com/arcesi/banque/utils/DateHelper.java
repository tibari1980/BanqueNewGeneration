package com.arcesi.banque.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class DateHelper {

	// -------------------------------------------------------- Constantes

	/** format JJ/MM/AA */
	public static final Pattern FORMAT_DATE_JJMMAA = Pattern
			.compile("([0][1-9]|[1-2][0-9]|[3][0-1])[/]([0][1-9]|[1][0-2])[/]([0-9]{2})");

	/** format JJ/MM/AAAA */
	public static final Pattern FORMAT_DATE_JJMMAAAA = Pattern
			.compile("([0][1-9]|[1-2][0-9]|[3][0-1])[/]([0][1-9]|[1][0-2])[/]([0-9]{4})");

	/** format AAAA-MM-JJ */
	public static final Pattern FORMAT_DATE_AAAAMMJJ = Pattern
			.compile("([0-9]{4})[-]([0][1-9]|[1][0-2])[-]([0][1-9]|[1-2][0-9]|[3][0-1])");

	// -------------------------------------------------------- Variables statiques

	// -------------------------------------------------------- Variables membres

	// -------------------------------------------------------- Constructeurs
	/**
	 * Constructeur privé
	 */
	private DateHelper() {
		// Constructeur privé
	}

	// -------------------------------------------------------- Méthodes public

	/**
	 * Renvoie la date du jour du système, pourra être modifier pour être calée sur
	 * une horloge externe
	 * 
	 * @return La date du jour
	 */
	public static Date getDateDuJour() {
		return new Date();
	}

	/**
	 * renvoie la date du jour su système sous forme d'objet Calendar
	 * 
	 * @return Calendar
	 */
	public static Calendar getCalendarDate() {
		final Calendar cal = new GregorianCalendar();
		cal.setTime(getDateDuJour());
		return cal;
	}

	/**
	 * renvoie la date en parametre sous forme d'objet Calendar
	 * 
	 * @param date date a transformer en calendar
	 * @return Calendar
	 */
	public static Calendar getCalendarDate(Date date) {
		final Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}

	/**
	 * Donne la date du premier jour du mois pour une date donnée. La date résultat
	 * est réglée à 00 h 00 min 00 s.
	 * 
	 * @param pDate Une date.
	 * @return Le premier jour du mois pour la date donnée, à 00 h 00 min 00 s.
	 */
	public static Date getDebutduMois(Date pDate) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(pDate);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = calendrier.get(Calendar.MONTH);
		final int jour = 1;
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date du premier jour du mois suivant pour une date donnée. La date
	 * résultat est réglée à 00 h 00 min 00 s.
	 * 
	 * @param pDate Une date.
	 * @return Le premier jour du mois suivant pour la date
	 *         donnée, à 00 h 00 min 00 s.
	 */
	public static Date getDebutDuMoisSuivant(final Date pDate) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(pDate);
		calendrier.add(Calendar.MONTH, 1);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = calendrier.get(Calendar.MONTH);
		final int jour = 1;
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date du dernier jour du mois en cours pour une date donnée (
	 * 23h59mn59s)
	 * 
	 * @param dateDuJour la date du jour
	 * @return le dernier jour du mois en cours pour une date donnée
	 */
	public static Date getFinDuMois(Date dateDuJour) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(dateDuJour);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = calendrier.get(Calendar.MONTH) + 1;
		final int jour = 01;
		final int heure = 23;
		final int minute = 59;
		final int seconde = 59;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		final Date premierPremierJourMoisProchain = calendrier.getTime();

		final Date finDeMois = getDatedansNJours(premierPremierJourMoisProchain, -1);

		return finDeMois;
	}

	/**
	 * Donne la date du dernier jour de l'année en cours (31/12 23 h 59 min 59 s)
	 * 
	 * @param dateDuJour la date du jour
	 * @return le 31 decembre de l'année en cours
	 */
	public static Date getFinAnneeEnCours(Date dateDuJour) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(dateDuJour);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = GregorianCalendar.DECEMBER;
		final int jour = 31;
		final int heure = 23;
		final int minute = 59;
		final int seconde = 59;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date du premier jour de l'année en cours (01/01 00h00mn00s)
	 * 
	 * @param dateDuJour la date du jour
	 * @return le 31 decembre de l'année en cours
	 */
	public static Date getDebutAnneeProchaine(Date dateDuJour) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(dateDuJour);
		final int annee = calendrier.get(Calendar.YEAR) + 1;
		final int mois = GregorianCalendar.JANUARY;
		final int jour = 1;
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date correspondante aux jour, mois, année passés en paramètre
	 * 
	 * @param jour  jour (de 1 à 31)
	 * @param mois  mois (de 1 à 12)
	 * @param annee année
	 * @return le date jour/mois/année
	 */
	public static Date getDate(int jour, int mois, int annee) {
		final Calendar calendrier = GregorianCalendar.getInstance();

		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois - 1, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Retourne la date correspondant au jour, mois, annee, heures, minutes et
	 * secondes en entree.
	 * 
	 * @param jour     jour
	 * @param mois     mois
	 * @param annee    annee
	 * @param heures   heure
	 * @param minutes  minute
	 * @param secondes seconde
	 * @return date
	 */
	public static Date getDate(int jour, int mois, int annee, int heures, int minutes, int secondes) {
		final Date date = getDate(jour, mois, annee);
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(date);
		calendrier.set(Calendar.HOUR_OF_DAY, heures);
		calendrier.set(Calendar.MINUTE, minutes);
		calendrier.set(Calendar.SECOND, secondes);
		return calendrier.getTime();
	}

	/**
	 * Donne la date du jour a minuit
	 * 
	 * @return la date du jour a minuit
	 */
	public static Date getDateDuJourAMinuit() {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(getDateDuJour());

		final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
		final int mois = calendrier.get(Calendar.MONTH);
		final int annee = calendrier.get(Calendar.YEAR);
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;

		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date mise en paramètre a minuit
	 * 
	 * @param date date
	 * @return la date mise en paramètre a minuit
	 */
	public static Date getDateAMinuit(Date date) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(date);

		final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
		final int mois = calendrier.get(Calendar.MONTH);
		final int annee = calendrier.get(Calendar.YEAR);
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;

		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Donne la date mise en paramètre a 23h59:59
	 * 
	 * @param date date
	 * @return la date mise en paramètre a 23h59:59
	 */
	public static Date getDateFinJour(Date date) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(date);

		final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
		final int mois = calendrier.get(Calendar.MONTH);
		final int annee = calendrier.get(Calendar.YEAR);
		final int heure = 23;
		final int minute = 59;
		final int seconde = 59;

		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Compare 2 dates par rapport aux jours, mois et annees
	 * 
	 * @param date1 date
	 * @param date2 date
	 * @return vrai si les 2 dates sont identiques
	 */
	public static boolean compareDates(final Date date1, final Date date2) {
		boolean result = false;
		if (date1 != null && date2 != null) {
			final Calendar cal1 = GregorianCalendar.getInstance();
			cal1.setTime(date1);
			final int jour1 = cal1.get(Calendar.DAY_OF_MONTH);
			final int mois1 = cal1.get(Calendar.MONTH);
			final int annee1 = cal1.get(Calendar.YEAR);

			final Calendar cal2 = GregorianCalendar.getInstance();
			cal2.setTime(date2);
			final int jour2 = cal2.get(Calendar.DAY_OF_MONTH);
			final int mois2 = cal2.get(Calendar.MONTH);
			final int annee2 = cal2.get(Calendar.YEAR);

			result = (jour1 == jour2) && (mois1 == mois2) && (annee1 == annee2);
		}
		return result;
	}

	/**
	 * Compare 2 dates par rapport aux jours, mois et annees
	 * 
	 * @param date1 date
	 * @param date2 date
	 * @param annee annee a comparer
	 * @return vrai si les 2 dates sont identiques ou si l'annee est egale à l'annee
	 *         de la date2
	 */
	public static boolean compareDatesEtAnnees(final Date date1, final Date date2, final String annee) {
		boolean result = false;
		if (date1 != null && date2 != null) {
			final Calendar cal1 = GregorianCalendar.getInstance();
			cal1.setTime(date1);
			final int jour1 = cal1.get(Calendar.DAY_OF_MONTH);
			final int mois1 = cal1.get(Calendar.MONTH);
			final int annee1 = cal1.get(Calendar.YEAR);

			final Calendar cal2 = GregorianCalendar.getInstance();
			cal2.setTime(date2);
			final int jour2 = cal2.get(Calendar.DAY_OF_MONTH);
			final int mois2 = cal2.get(Calendar.MONTH);
			final int annee2 = cal2.get(Calendar.YEAR);

			result = (jour1 == jour2) && (mois1 == mois2) && (annee1 == annee2);

			if (!result && annee != null) {
				result = annee.equals(String.valueOf(annee2));
			}
		}

		return result;
	}

	/**
	 * compare deux dates avec uniquement l'année, le mois et le jour de chacune
	 * d'elles. Utile pour les comparaisons de date de fin de validité.
	 * 
	 * @param dateA date à comparer
	 * @param dateB date à comparer
	 * @return true si la date A et strictement posterieure à la date B et false
	 *         sinon
	 */
	public static boolean compareAnneeMoisJourDateAStrictAfter(Date dateA, Date dateB) {
		boolean compar = false;
		final Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateA);
		final int anneeDateA = cal.get(Calendar.YEAR);
		final int moisDateA = cal.get(Calendar.MONTH);
		final int jourDateA = cal.get(Calendar.DAY_OF_MONTH);

		final Calendar cal2 = GregorianCalendar.getInstance();
		cal2.setTime(dateB);
		final int anneeDateB = cal2.get(Calendar.YEAR);
		final int moisDateB = cal2.get(Calendar.MONTH);
		final int jourDateB = cal2.get(Calendar.DAY_OF_MONTH);

		if (anneeDateA > anneeDateB) {
			compar = true;
		} else if (anneeDateA == anneeDateB) {
			if (moisDateA > moisDateB) {
				compar = true;
			} else if (moisDateA == moisDateB) {
				if (jourDateA > jourDateB) {
					compar = true;
				}
			}
		}

		return compar;
	}

	/**
	 * Compare 2 dates par rapport aux minutes, heures, jours, mois et annees
	 * 
	 * @param date1 date
	 * @param date2 date
	 * 
	 * @return vrai si les 2 dates sont identiques
	 */
	public static boolean compareDatesJusquAuxMinutes(final Date date1, final Date date2) {
		boolean result = false;

		if (date1 != null && date2 != null) {
			final Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			final int minute1 = cal1.get(Calendar.MINUTE);
			final int heure1 = cal1.get(Calendar.HOUR_OF_DAY);
			final int jour1 = cal1.get(Calendar.DAY_OF_MONTH);
			final int mois1 = cal1.get(Calendar.MONTH);
			final int annee1 = cal1.get(Calendar.YEAR);

			final Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			final int minute2 = cal2.get(Calendar.MINUTE);
			final int heure2 = cal2.get(Calendar.HOUR_OF_DAY);
			final int jour2 = cal2.get(Calendar.DAY_OF_MONTH);
			final int mois2 = cal2.get(Calendar.MONTH);
			final int annee2 = cal2.get(Calendar.YEAR);

			result = (minute1 == minute2) && (heure1 == heure2) && (jour1 == jour2) && (mois1 == mois2)
					&& (annee1 == annee2);
		}

		return result;
	}

	/**
	 * Verifie si 2 dates sont identiques
	 * 
	 * @param date1 - la premiere date
	 * @param date2 - la seconde date
	 * @return vrai si les 2 dates sont identiques
	 */
	public static boolean isDateIdentique(final Date date1, final Date date2) {
		boolean result = false;

		if (date1 != null && date2 != null) {
			result = date1.getTime() == date2.getTime();
		}

		return result;
	}

	/**
	 * Ajoute un nombre de millisecondes a une date et retourne un nouvel objet.<br>
	 * L'objet date original reste inchange
	 * 
	 * @param date   - la date, non null
	 * @param valeur - le nombre de ms, peut etre negatif
	 * @return le nouvel objet date avec le nombre de ms ajouté
	 */
	public static Date addMilliseconds(final Date date, final int valeur) {
		return add(date, Calendar.MILLISECOND, valeur);
	}

	/**
	 * Ajoute un nombre de secondes a une date et retourne un nouvel objet.<br>
	 * L'objet date original reste inchange
	 * 
	 * @param date   - la date, non null
	 * @param valeur - le nombre de sec, peut etre negatif
	 * @return le nouvel objet date avec le nombre de sec ajouté
	 */
	public static Date addSeconds(final Date date, final int valeur) {
		return add(date, Calendar.SECOND, valeur);
	}

	/**
	 * Retourne les secondes de la date en entree.
	 * 
	 * @param date date
	 * @return secondes
	 */
	public static int getSeconds(final Date date) {
		return get(date, Calendar.SECOND);
	}

	/**
	 * Retourne les minutes de la date en entree.
	 * 
	 * @param date date
	 * @return minutes
	 */
	public static int getMinutes(final Date date) {
		return get(date, Calendar.MINUTE);
	}

	/**
	 * Retourne les heures [0-23] de la date en entree.
	 * 
	 * @param date date
	 * @return heures
	 */
	public static int getHourOfDay(final Date date) {
		return get(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * Retourne le jour [1-31] de la date en entree.
	 * 
	 * @param date date
	 * @return jour
	 */
	public static int getDayOfMonth(final Date date) {
		return get(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * Retourne le mois [1-12] de la date en entree.
	 * 
	 * @param date date
	 * @return mois
	 */
	public static int getMonth(final Date date) {
		return get(date, Calendar.MONTH) + 1;
	}

	/**
	 * Retourne l'annee de la date en entree.
	 * 
	 * @param date date
	 * @return annee
	 */
	public static int getYear(final Date date) {
		return get(date, Calendar.YEAR);
	}

	/**
	 * Donne la date dans n mois
	 * 
	 * @param nbMois nombre de mois à ajouter
	 * @return le date
	 */
	public static Date getDatedansNMois(final int nbMois) {
		return getDatedansNMois(getDateDuJour(), nbMois);
	}

	/**
	 * Donne la date dans n mois a partir d'une date donnee
	 * 
	 * @param date   date a laquelle on ajoute le nb de mois
	 * @param nbMois nombre de mois à ajouter
	 * @return le date
	 */
	public static Date getDatedansNMois(final Date date, final int nbMois) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(date);
		calendrier.add(Calendar.MONTH, nbMois);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = calendrier.get(Calendar.MONTH);
		final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);
		return calendrier.getTime();
	}

	/**
	 * Donne la date dans n jours
	 * 
	 * @param nbJours nombre de jours à ajouter
	 * @return la date
	 */
	public static Date getDatedansNJours(final int nbJours) {
		return getDatedansNJours(getDateDuJour(), nbJours);
	}

	/**
	 * Donne la date dans n jours a partir d'une date donnee
	 * 
	 * @param date    date a laquelle on ajoute le nb de jours
	 * @param nbJours nombre de jours à ajouter
	 * @return la date
	 */
	public static Date getDatedansNJours(final Date date, final int nbJours) {
		final Calendar calendrier = GregorianCalendar.getInstance();
		calendrier.setTime(date);
		calendrier.add(Calendar.DAY_OF_MONTH, nbJours);
		final int annee = calendrier.get(Calendar.YEAR);
		final int mois = calendrier.get(Calendar.MONTH);
		final int jour = calendrier.get(Calendar.DAY_OF_MONTH);
		final int heure = 0;
		final int minute = 0;
		final int seconde = 0;
		calendrier.clear();
		calendrier.set(annee, mois, jour, heure, minute, seconde);

		return calendrier.getTime();
	}

	/**
	 * Indique si le nombre d'années séparant les deux dates est inférieur ou
	 * supérieur à la valeur passée en paramètre.
	 * 
	 * @param date1 première date
	 * @param date2 seconde date
	 * @param ecart d'années d'écart de référence
	 * @return comparaison de l'écart réel et de l'écart de référence
	 */
	public static Boolean isEcartInferieurOuEgal(final Date date1, final Date date2, final int ecart) {
		Boolean resultat;

		if (date1 == null || date2 == null || date1.compareTo(date2) > 0 || ecart < 0) {
			resultat = null;
		} else {
			final Calendar dateAnt = new GregorianCalendar();
			final Calendar datePost = new GregorianCalendar();
			final Calendar dateComp = new GregorianCalendar();
			dateAnt.setTime(date1);
			datePost.setTime(date2);
			// calcul de : date2 - "ecart" années
			final int anneePost = datePost.get(Calendar.YEAR);
			final int moisPost = datePost.get(Calendar.MONTH);
			final int jourPost = datePost.get(Calendar.DAY_OF_MONTH);
			dateComp.set(Calendar.YEAR, anneePost - ecart);
			dateComp.set(Calendar.MONTH, moisPost);
			if (moisPost == Calendar.FEBRUARY && jourPost == 29) {
				dateComp.set(Calendar.DAY_OF_MONTH, 28);
			} else {
				dateComp.set(Calendar.DAY_OF_MONTH, jourPost);
			}
			// comparaison de dateAnt et dateComp
			if (dateAnt.compareTo(dateComp) < 0) {
				resultat = Boolean.FALSE;
			} else {
				resultat = Boolean.TRUE;
			}
		}

		return resultat;
	}

	/**
	 * indique si la date passée en paramètre est au bon format
	 * 
	 * @param motif motif pour la correspondance ("pattern" pour le "match")
	 * @param date  date dont le format est à valider
	 * @return le format de la date est valide
	 */
	public static boolean isDateValide(final String motif, final String date) {
		boolean isValide = false;

		if (date != null && (FORMAT_DATE_JJMMAA.equals(motif) || FORMAT_DATE_JJMMAAAA.equals(motif)
				|| FORMAT_DATE_AAAAMMJJ.equals(motif))) {
			isValide = Pattern.matches(motif, date.subSequence(0, date.length()));
		}

		return isValide;
	}

	/**
	 * Transforme une date en String au format hh:mm
	 * 
	 * @param sDate date a convertir en string
	 * @return String représentant une date au format hh:mm
	 * @throws ConvertException si le format n'est pas correct
	 */
	public static String heureToString(Date sDate) {
		final Calendar calHour = getCalendarDate(sDate);
		final int heure = calHour.get(Calendar.HOUR_OF_DAY);
		final String h = String.valueOf(heure);
		final int minute = calHour.get(Calendar.MINUTE);
		String m = String.valueOf(minute);
		if (m.length() == 1) {
			m = "0" + m;
		}
		return h + "h" + m;
	}

	/**
	 * Retourne la p_NbJours ème date ouvrée suivant celle donnée en paramètre.
	 * <br />
	 * Si p_NbJour est égal à 0, renvoie la p_Date qu'elle soit ouvrée ou non.
	 * 
	 * @param p_Date    - Date - le jour à évaluer
	 * @param p_NbJours - int - le nombre de jours ouvrés suivant
	 * @return le jour ouvré
	 */
	public static Date getJourOuvreSuivant(final Date p_Date, final int p_NbJours) {
		final Calendar lCal = Calendar.getInstance();
		lCal.setTime(p_Date);

		Date lDateRetour = lCal.getTime();
		if (p_NbJours != 0) {
			int lDay = lCal.get(Calendar.DAY_OF_WEEK);
			if (lDay == Calendar.FRIDAY) {
				lCal.add(Calendar.DAY_OF_MONTH, 3);
			} else if (lDay == Calendar.SATURDAY) {
				lCal.add(Calendar.DAY_OF_MONTH, 2);
			} else {
				lCal.add(Calendar.DAY_OF_MONTH, 1);
			}

			// est-ce que la date est un jour ferie ?
			boolean lFerie = isJourFerie(lCal.getTime());
			while (lFerie) {
				lDay = lCal.get(Calendar.DAY_OF_WEEK);
				if (lDay == Calendar.FRIDAY) {
					lCal.add(Calendar.DAY_OF_MONTH, 3);
				} else {
					lCal.add(Calendar.DAY_OF_MONTH, 1);
				}

				lFerie = isJourFerie(lCal.getTime());
			}

			lDateRetour = getJourOuvreSuivant(lCal.getTime(), p_NbJours - 1);
		}

		return lDateRetour;
	}

	/**
	 * Retourne la p_NbJours ème date ouvrée précédent celle donnée en paramètre.
	 * <br />
	 * Si p_NbJour est égal à 0, renvoie la p_Date qu'elle soit ouvrée ou non.
	 * 
	 * @param p_Date    - Date - le jour à évaluer
	 * @param p_NbJours - int - le nombre de jours ouvrés précédent
	 * @return le jour ouvré
	 */
	public static Date getJourOuvrePrecedant(final Date p_Date, final int p_NbJours) {
		final Calendar lCal = Calendar.getInstance();
		lCal.setTime(p_Date);

		Date lDateRetour = lCal.getTime();
		if (p_NbJours != 0) {
			int lDay = lCal.get(Calendar.DAY_OF_WEEK);
			if (lDay == Calendar.MONDAY) {
				lCal.add(Calendar.DAY_OF_MONTH, -3);
			} else if (lDay == Calendar.SUNDAY) {
				lCal.add(Calendar.DAY_OF_MONTH, -2);
			} else {
				lCal.add(Calendar.DAY_OF_MONTH, -1);
			}

			// est-ce que la date est un jour ferie ?
			boolean lFerie = isJourFerie(lCal.getTime());
			while (lFerie) {
				lDay = lCal.get(Calendar.DAY_OF_WEEK);
				if (lDay == Calendar.MONDAY) {
					lCal.add(Calendar.DAY_OF_MONTH, -3);
				} else {
					lCal.add(Calendar.DAY_OF_MONTH, -1);
				}
				lFerie = isJourFerie(lCal.getTime());
			}

			lDateRetour = getJourOuvrePrecedant(lCal.getTime(), p_NbJours - 1);
		}

		return lDateRetour;
	}

	// -------------------------------------------------------- Méthodes protected

	// -------------------------------------------------------- Méthodes privée

	/**
	 * Ajoute a une date et retourne un nouvel objet.<br>
	 * L'objet original reste inchange
	 * 
	 * @param date          - la date, non null
	 * @param calendarField - le calendar field a ajouter
	 * @param valeur        - la valeur a ajouter, peut etre negative
	 * @return le nouvel objet date avec la valeur ajoute
	 * @throws java.lang.IllegalArgumentException - si la date est nulle
	 */
	private static Date add(Date date, int calendarField, int valeur) {
		Date newDate = null;

		if (date == null) {
			throw new IllegalArgumentException("La date ne doit pas etre null");
		} else {
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(calendarField, valeur);
			newDate = c.getTime();
		}

		return newDate;
	}

	/**
	 * Retourne la valeur du champ date en entree
	 * 
	 * @param date          - la date, non null
	 * @param calendarField - le calendar field a retourner
	 * @return la valeur du champ date
	 */
	private static int get(Date date, int calendarField) {
		if (date == null) {
			throw new IllegalArgumentException("La date ne doit pas etre null");
		} else {
			final Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(calendarField);
		}
	}

	/**
	 * Donne la date la plus tardive parmi deux dates.
	 * 
	 * @param pDateA Une date, non <code>null</code>.
	 * @param pDateB Une autre date, non <code>null</code>.
	 * @return La date la plus tardive des deux.
	 */
	public static Date max(final Date pDateA, final Date pDateB) {
		Date datePlusTardive;

		if (pDateB.after(pDateA)) {
			datePlusTardive = pDateB;
		} else {
			datePlusTardive = pDateA;
		}
		return datePlusTardive;
	}

	/**
	 * Détermine si la date est un jour férié
	 * 
	 * @param p_Date - Date - la date à évaluer
	 * @return true si la date est un jour férié, false sinon
	 */
	public static boolean isJourFerie(final Date p_Date) {
		boolean lRetour = false;

		final Calendar lCal = Calendar.getInstance();
		lCal.setTime(p_Date);

		final Map<String, Date> lJoursFeries = obtenirJoursFeries(lCal.get(Calendar.YEAR));
		final Collection<Date> lJFeries = lJoursFeries.values();

		for (final Iterator iter = lJFeries.iterator(); iter.hasNext();) {
			final Date lDateFerie = (Date) iter.next();
			if (compareDates(p_Date, lDateFerie)) {
				lRetour = true;
			}
		}

		return lRetour;
	}

	/**
	 * Recupere les dates des jours feries
	 * 
	 * @param p_Year annee
	 * @return liste des jours feries
	 */
	private static Map<String, Date> obtenirJoursFeries(final int p_Year) {
		// Calcul de la date de Paques
		int lJourPaques = 0;
		int lMoisPaques = 0;
		// 1. le reste de m/19 : c’est la valeur de a
		final int lResteA = p_Year % 19;

		// 2. le reste de m/4 : c’est la valeur de b
		final int lResteB = p_Year % 4;

		// 3. le reste de m/7 : c’est la valeur de c
		final int lResteC = p_Year % 7;

		// 4. le reste de (19a + 24)/30 : c’est la valeur de d
		final int lResteD = (19 * lResteA + 24) % 30;

		// 5. le reste de(2b + 4c + 6d + 5)/7 : c’est la valeur de e
		final int lResteE = (2 * lResteB + 4 * lResteC + 6 * lResteD + 5) % 7;

		// La date de Paques est le (22 + d + e) mars ou le (d + e - 9) avril
		final int lJour = 22 + lResteD + lResteE;
		if (lJour > 31) {
			lJourPaques = lJour - 31;
			lMoisPaques = Calendar.APRIL;
		} else {
			lJourPaques = lJour;
			lMoisPaques = Calendar.MARCH;
		}

		// Paques
		final Calendar lCal = Calendar.getInstance();
		lCal.set(p_Year, lMoisPaques, lJourPaques, 0, 0, 0);
		lCal.set(Calendar.MILLISECOND, 0);

		// Lundi de Paques
		lCal.add(Calendar.DAY_OF_MONTH, 1);
		final Date lFeriePaques = lCal.getTime();

		// Ascension a lieu 39 jours apres Paques
		lCal.add(Calendar.DAY_OF_MONTH, 38);
		final Date lDateAscension = lCal.getTime();

		// Lundi de Pentecote a lieu 49 jours apres Paques
		lCal.add(Calendar.DAY_OF_MONTH, 10);
		final Date lDatePentecote = lCal.getTime();

		// 1er Mai
		lCal.set(p_Year, Calendar.MAY, 1, 0, 0, 0);
		final Date lFerieTravail = lCal.getTime();

		// 8 Mai
		lCal.set(p_Year, Calendar.MAY, 8, 0, 0, 0);
		final Date lFerieHuitMai = lCal.getTime();

		// 1er Janv
		lCal.set(p_Year, Calendar.JANUARY, 1, 0, 0, 0);
		final Date lFerieNvlAn = lCal.getTime();

		// 25 Dec
		lCal.set(p_Year, Calendar.DECEMBER, 25, 0, 0, 0);
		final Date lFerieNoel = lCal.getTime();

		// 14 Juillet
		lCal.set(p_Year, Calendar.JULY, 14, 0, 0, 0);
		final Date lFerieFeteNat = lCal.getTime();

		// 1er Nov
		lCal.set(p_Year, Calendar.NOVEMBER, 1, 0, 0, 0);
		final Date lFerieToussaint = lCal.getTime();

		// 11 Nov
		lCal.set(p_Year, Calendar.NOVEMBER, 11, 0, 0, 0);
		final Date lFerieOnzeNov = lCal.getTime();

		// 15 Aout
		lCal.set(p_Year, Calendar.AUGUST, 15, 0, 0, 0);
		final Date lFerieAssomption = lCal.getTime();

		final Map<String, Date> lRetour = new HashMap<String, Date>();
		lRetour.put(JoursFeriesConstantes.JF_PAQUES, lFeriePaques);
		lRetour.put(JoursFeriesConstantes.JF_ASCENSION, lDateAscension);
		lRetour.put(JoursFeriesConstantes.JF_PENTECOTE, lDatePentecote);
		lRetour.put(JoursFeriesConstantes.JF_TRAVAIL, lFerieTravail);
		lRetour.put(JoursFeriesConstantes.JF_HUIT_MAI, lFerieHuitMai);
		lRetour.put(JoursFeriesConstantes.JF_NOEL, lFerieNoel);
		lRetour.put(JoursFeriesConstantes.JF_NVL_AN, lFerieNvlAn);
		lRetour.put(JoursFeriesConstantes.JF_FETE_NATIONALE, lFerieFeteNat);
		lRetour.put(JoursFeriesConstantes.JF_TOUSSAINT, lFerieToussaint);
		lRetour.put(JoursFeriesConstantes.JF_ONZE_NOVEMBRE, lFerieOnzeNov);
		lRetour.put(JoursFeriesConstantes.JF_ASSOMPTION, lFerieAssomption);

		return lRetour;
	}

	/**
	 * Conversion d'une date YYYYMMdd en dd-MM-yyyy
	 * 
	 * @param dateAsStringYYYYMMdd date à convertir
	 * @return dateAsStringjjmmAAAA
	 * @throws ParseException si la date n'est oas au format attendu
	 */
	public static String convertDateFromYYYYMMddToJjmmAAAA(String dateAsStringYYYYMMdd) throws ParseException {
		Date dateAsDate = null;
		if (dateAsStringYYYYMMdd != null) {
			final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateAsDate = formatter.parse(dateAsStringYYYYMMdd);
		}
		final String dateAsStringjjmmAAAA = new SimpleDateFormat("dd-MM-yyyy").format(dateAsDate);
		return dateAsStringjjmmAAAA;
	}

	/**
	 * Compare les heures et minutes de 2 dates
	 * 
	 * @param date1 date
	 * @param date2 date
	 * 
	 * @return vrai si les 2 dates sont identiques en terme d'heure et minutes
	 */
	public static boolean compareHeuresMinutesDates(final Date date1, final Date date2) {
		boolean result = false;

		if (date1 != null && date2 != null) {
			final Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			final int heure1 = cal1.get(Calendar.HOUR_OF_DAY);
			final int minute1 = cal1.get(Calendar.MINUTE);

			final Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			final int heure2 = cal2.get(Calendar.HOUR_OF_DAY);
			final int minute2 = cal2.get(Calendar.MINUTE);

			result = (heure1 == heure2) && (minute1 == minute2);
		}

		return result;
	}

	// -------------------------------------------------------- Accesseurs

	// -------------------------------------------------------- Inner classes

}
