package com.epimarket.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epimarket.database.EMF;
import com.epimarket.entity.Purchase;

@Controller
@RequestMapping("stat")
public class StatisticController
{
	@RequestMapping(
			value = "/monthly",
			method = RequestMethod.GET)
	public String monthly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Criteria				crit		= null;
		//DefaultPieDataset		pieDataSet	= new DefaultPieDataset();
		GregorianCalendar		cal			= new GregorianCalendar();
		SimpleDateFormat		fo			= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date	end	= cal.getTime();
		cal.add(Calendar.MONTH, -6);
		Date	begin = cal.getTime();
		//System.out.println("begin:" + begin);
		//System.out.println("end:" + end);
		
		String		pieValue	= new String();
		String		pieName		= new String();
		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?>		l = crit.list();
			//System.out.println("ICCICIIIIIIIIIIIIIIIIIIi =========>" + l.size());
			//pieDataSet.setValue(i.toString(), new Integer(l.size()));
			pieValue += ((Integer)l.size()).toString() + ",";
			pieName += i.toString() + "|";
			crit.list().clear();
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.ge("nbArticles", 5));
		try {
			crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
		} catch (ParseException e) { e.printStackTrace(); }
		pieValue += ((Integer)crit.list().size()).toString();
		pieName += "5 et plus";
		rqst.setAttribute("pieValue", pieValue);
		rqst.setAttribute("pieName", pieName);
		rqst.setAttribute("pieTitle", "Nombre moyen de livres par commande au cours des 6 derniers mois");

		end = cal.getTime();
		String	barValue1	= new String();
		String	barValue2	= new String();
		String	barBottom	= "|5|4|3|2|1|0";
		Integer	max1		= 0;
		Double	max2		= 0.0;
		for (Integer i = 5; i >= 0; --i) {
			begin = end;
			cal.add(Calendar.MONTH, 1);
			end = cal.getTime();
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?> l = crit.list();
			barValue1 += ((Integer)l.size()).toString();
			if (l.size() > max1) { max1 = l.size(); }
			//ds.setValue(l.size(), "nbCommands", i.toString());
			Double sum = 0.0;
			for (Object elm : l) {
				sum += ((Purchase) elm).getFullPrice();
				//System.out.println("===>" + sum);
			}
			System.out.println("===>" + l.size());
			if (sum > max2) { max2 = sum; }
			barValue2 += sum.toString();
			//ds2.addValue(sum, "Total sold", i.toString());
			if (i > 0) {
				barValue1 += ",";
				barValue2 += ",";
			}
		}
		String sMax1 = new String();
		String sMax2 = new String();
		for (int i = 0; i <= 4; ++i) {
			sMax1 += ("|" + ((Integer)(max1 * i / 4)).toString());
			sMax2 += ("|" + ((Double)(max2 / 4.0 * i)).toString());
		}
		System.out.println("::" + barValue1);
		System.out.println("::" + max1);
		rqst.setAttribute("bTitle1", "Nombre de commandes par mois (6 mois)");
		rqst.setAttribute("bTitle2", "Total des commandes par mois (6 mois)");
		rqst.setAttribute("barBottom", barBottom);
		rqst.setAttribute("barValue1", barValue1);
		rqst.setAttribute("barValue2", barValue2);
		rqst.setAttribute("max1", sMax1);
		rqst.setAttribute("max2", sMax2);
		return "displayStat";
	}

	@RequestMapping(
			value = "/weekly",
			method = RequestMethod.GET)
	public String weekly(HttpServletRequest rqst, HttpServletResponse resp, Model model)
	{
		Criteria				crit		= null;
		DefaultPieDataset		pieDataSet	= new DefaultPieDataset();
		GregorianCalendar		cal			= new GregorianCalendar();
		SimpleDateFormat		fo			= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		cal.add(Calendar.DAY_OF_WEEK, 1);
		Date	end	= cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, -8);
		Date	begin = cal.getTime();
		//System.out.println("begin:" + begin);
		//System.out.println("end:" + end);
		
		String		pieValue	= new String();
		String		pieName		= new String();
		for (Integer i = 1; i < 5; ++i) {
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			crit.add(Restrictions.eq("nbArticles", i));
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?>		l = crit.list();
			//System.out.println("ICCICIIIIIIIIIIIIIIIIIIi =========>" + l.size());
			//pieDataSet.setValue(i.toString(), new Integer(l.size()));
			pieValue += ((Integer)l.size()).toString() + ",";
			pieName += i.toString() + "|";
			crit.list().clear();
		}
		crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
		crit.add(Restrictions.ge("nbArticles", 5));
		try {
			crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
		} catch (ParseException e) { e.printStackTrace(); }
		pieValue += ((Integer)crit.list().size()).toString();
		pieName += "5 et plus";
		rqst.setAttribute("pieValue", pieValue);
		rqst.setAttribute("pieName", pieName);
		rqst.setAttribute("pieTitle", "Nombre moyen de livres par commande au cours des 7 derniers jours");

		end = cal.getTime();
		String	barValue1	= new String();
		String	barValue2	= new String();
		String	barBottom	= "|6|5|4|3|2|1|0";
		Integer	max1		= 0;
		Double	max2		= 0.0;
		for (Integer i = 6; i >= 0; --i) {
			begin = end;
			cal.add(Calendar.DAY_OF_WEEK, 1);
			end = cal.getTime();
			crit = EMF.getSession().createCriteria(Purchase.class.getCanonicalName());
			try {
				crit.add(Restrictions.between("date", (Date)(fo.parse(fo.format(begin))), (Date)(fo.parse(fo.format(end)))));
			} catch (ParseException e) { e.printStackTrace(); }
			List<?> l = crit.list();
			barValue1 += ((Integer)l.size()).toString();
			if (l.size() > max1) { max1 = l.size(); }
			//ds.setValue(l.size(), "nbCommands", i.toString());
			Double sum = 0.0;
			for (Object elm : l) {
				sum += ((Purchase) elm).getFullPrice();
				//System.out.println("===>" + sum);
			}
			System.out.println("===>" + l.size());
			if (sum > max2) { max2 = sum; }
			barValue2 += sum.toString();
			//ds2.addValue(sum, "Total sold", i.toString());
			if (i > 0) {
				barValue1 += ",";
				barValue2 += ",";
			}
		}
		String sMax1 = new String();
		String sMax2 = new String();
		for (int i = 0; i <= 4; ++i) {
			sMax1 += ("|" + ((Integer)(max1 * i / 4)).toString());
			sMax2 += ("|" + ((Double)(max2 / 4.0 * i)).toString());
		}
		System.out.println("::" + barValue1);
		System.out.println("::" + max1);
		rqst.setAttribute("bTitle1", "Nombre de commandes par jour (7 jours)");
		rqst.setAttribute("bTitle2", "Total des commandes par jour (7 jours)");
		rqst.setAttribute("barBottom", barBottom);
		rqst.setAttribute("barValue1", barValue1);
		rqst.setAttribute("barValue2", barValue2);
		rqst.setAttribute("max1", sMax1);
		rqst.setAttribute("max2", sMax2);
		return "displayStat";
	}

}