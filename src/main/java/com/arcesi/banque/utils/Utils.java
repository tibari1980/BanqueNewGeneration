package com.arcesi.banque.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.Pair;

public class Utils {

	/**
	 * Renvoie les valeurs de la hashMap sous forme de liste
	 * 
	 * @param map HashMap contenant les valeurs
	 * @param <T> le type de l'objet attendu, celui de la HashMap
	 * @return la liste avec les valeurs de la hashMap
	 */
	public static <T> List<T> getListe(final Map<String, T> map) {
		List<T> liste = null;
		if (map != null) {
			liste = new ArrayList<T>(map.values());
		}
		return liste;
	}

	/**
	 * Methode qui renvoie true si l'objet Boolean n'est pas null et vaut TRUE
	 * 
	 * @param b Boolean à analyser
	 * @return true si l'objet Boolean n'est pas null et vaut TRUE
	 */
	public static boolean isBooleanTrue(Boolean b) {
		return (b != null) && (b.equals(Boolean.TRUE));
	}

	/**
	 * Methode qui renvoie false si l'objet Boolean est null ou vaut FASLE
	 * 
	 * @param b Boolean à analyser
	 * @return true si l'objet Boolean est null ou vaut FASLE
	 */
	public static boolean isBooleanFalse(Boolean b) {
		return (b == null) || (b.equals(Boolean.FALSE));
	}

	/**
	 * Recupere un sous ensemble d'un enumeration, en passant en paramettre les
	 * valeurs à exlure
	 * 
	 * @param <T>              le type d'enumeration
	 * @param valuesToExclude  1ere valeur a eclure
	 * @param valuesToExcludes les valeur a exclure du sous ensemble
	 * @return un sous ensemble de T sans valuesToExcludes
	 */
	public static <T extends Enum<T>> EnumSet<T> excludeEnumValue(final T valuesToExclude,
			final T... valuesToExcludes) {
		final EnumSet<T> toExclude = EnumSet.noneOf(valuesToExclude.getDeclaringClass());
		toExclude.add(valuesToExclude);
		for (final T valueToExcludes : valuesToExcludes) {
			toExclude.add(valueToExcludes);
		}
		final EnumSet<T> result = EnumSet.complementOf(toExclude);
		return result;
	}

	/**
	 * Rends les EnumSet pour etre utilisé dans les clause in sql
	 * 
	 * @param enumSet l'enumSet
	 * @return la chaine de caracter représentant l'EnumSet
	 */
	public static String renderEnumSet(final EnumSet<? extends Enum<?>> enumSet) {
		final StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (final Enum<? extends Enum<?>> value : enumSet) {
			if (!first) {
				sb.append(", ");
			} else {
				first = false;
			}
			sb.append("'").append(value).append("'");
		}

		return sb.toString();
	}

	/**
	 * Convertit un EnumSet en list de de String
	 * 
	 * @param enumSet l'enumSet
	 * @return la liste de String valeur de l'enumerations
	 */
	public static List<String> convertEnumSet(final EnumSet<? extends Enum<?>> enumSet) {
		final List<String> result = new ArrayList<String>(enumSet.size());
		for (final Enum<? extends Enum<?>> value : enumSet) {
			result.add(String.valueOf(value));
		}
		return result;
	}

	/**
	 * Extrait la liste des seconds UNIQUES de la liste de Pair.<br />
	 * Exemple :<br />
	 * liste = Pair(a, b), Pair(c, d), Pair(e, d)<br />
	 * extraireSeconds(liste) => (b, d)
	 * 
	 * @param listePairs la {@link List} de {@link Pair}
	 * @return la liste des seconds ou une liste vide si le paramètre est null ou
	 *         vide
	 */
	public static List<String> extraireSeconds(final List<Pair<String, String>> listePairs) {
		final List<String> result = new ArrayList<String>();
		if (listePairs != null && !listePairs.isEmpty()) {
			for (Pair pair : listePairs) {
				final String second = (String) pair.getSecond();
				if (!result.contains(second)) {
					result.add(second);
				}
			}
		}
		return result;
	}

	/**
	 * <p>
	 * Calcule la somme des éléments d'une liste de BigDecimal. Les
	 * éléments <code>null</code> sont considérés comme valant 0.
	 * </p>
	 * 
	 * @param uneListe La liste.
	 * @return La somme des éléments de la liste.
	 */
	public static BigDecimal somme(List<BigDecimal> uneListe) {
		BigDecimal sommeEnCours = new BigDecimal(0);
		for (BigDecimal unElement : uneListe) {
			/* On pense au cas spécial null ! */
			if (unElement != null) {
				sommeEnCours = sommeEnCours.add(unElement);
			}
		}
		return sommeEnCours;
	}

	/**
	 * Arrondi mathématique des BigDecimal (parce que je comprends rien à leur
	 * fonction d'arrondi...) (celle-ci est exacte !)
	 * 
	 * @param bgIn le big decimal à arrondir
	 * @return bigOut le big decimal arrondi
	 */
	public static BigDecimal arrondir(final BigDecimal bgIn) {
		BigDecimal bgOut = new BigDecimal(0);
		long bi = bgIn.unscaledValue().longValue();
		long d = 0;
		long m = 0;
		for (long j = 0; j < bgIn.scale(); j++) {
			d = bi - ((bi / 10) * 10);
			m = 0;
			if (d >= 5) {
				m = 1;
			}
			bi = bi / 10;
		}
		bi = bi + m;
		bgOut = BigDecimal.valueOf(bi, 0);
		return bgOut;
	}

	//@formatter:off
   /**
    * <p>Teste l'égalité de deux objets, qui peuvent être <code>null</code>.</p>
    * 
    * @param objet1 Un objet, éventuellement <code>null</code>.
    * @param objet2 Un autre objet, éventuellement <code>null</code>.
    * 
    * @return <code>true</code> SI ET SEULEMENT SI [ les objets sont tous les deux <code>null</code> OU les objets sont égaux au sens de leur méthode <code>equals</code> ].
    */
   public static boolean equalNullSafe(Object objet1, Object objet2)
   //@formatter:on
	{
		final boolean resultat;
		if (objet1 == null) {
			resultat = (objet2 == null);
		} else {
			resultat = objet1.equals(objet2);
		}
		return resultat;
	}

	//@formatter:off
   /**
    * <p>Renvoie le résultat de la méthode <code>toString()</code>, ou <code>(null)</code> si l'objet est <code>null</code>.</p>
    * 
    * @param objet1 Un objet, éventuellement <code>null</code>.
    * 
    * @return Le résultat de la méthode <code>objet1.toString()</code>, ou <code>(null)</code> si <code>objet1</code> est <code>null</code>.
    */
   public static String toStringNullSafe(Object objet1)
   //@formatter:on
	{
		final String resultat;
		if (objet1 == null) {
			resultat = "(null)";
		} else {
			resultat = objet1.toString();
		}
		return resultat;
	}

	/**
	 * Méthode permettant de comparer deux variables A et B
	 * 
	 * @param a valeur de A
	 * @param b la valeur de B second
	 * @return true si true si(A, B)sont egaux.
	 */
	public static boolean compare(final Boolean a, final Boolean b) {
		boolean resultat = true;
		if (a != null) {
			resultat = a.equals(b);
		}
		if (b != null) {
			resultat = b.equals(a);
		}
		return resultat;
	}

	/**
	 * Méthode permettant de comparer deux BigDecimal A et B
	 * 
	 * @param a {@link BigDecimal}
	 * @param b {@link BigDecimal}
	 * @return true si true si(A, B)sont egaux.
	 */
	public static boolean equalsBigDecimal(final BigDecimal a, final BigDecimal b) {
		final boolean resultat;
		if (a == null || b == null) {
			resultat = a == b;
		} else {
			resultat = a.equals(b);
		}
		return resultat;
	}

	/**
	 * Méthode permettant de comparer deux Boolean en considérant un boolean null
	 * comme false
	 * 
	 * @param b1 premier boolean
	 * @param b2 second boolean
	 * @return true si b1 et b2 sont égaux, sinon false
	 */
	public static boolean compareBoolean(final Boolean b1, final Boolean b2) {
		boolean boolean1 = false;
		boolean boolean2 = false;

		if (b1 != null) {
			boolean1 = b1.booleanValue();
		}
		if (b2 != null) {
			boolean2 = b2.booleanValue();
		}

		return boolean1 == boolean2;
	}

	

}
