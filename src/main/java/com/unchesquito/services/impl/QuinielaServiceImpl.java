package com.unchesquito.services.impl;

import java.io.IOException;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unchesquito.request.MatchesRequest;
import com.unchesquito.request.LayoutRequest;

import jakarta.servlet.http.HttpServletResponse;

public class QuinielaServiceImpl  {

	LayoutRequest request;

	public QuinielaServiceImpl(LayoutRequest request) {

		this.request = request;

	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {

		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(15);
		// Creating paragraph

		for (int i = 0; i < 3; i++) {

			Paragraph paragraph = new Paragraph("Sacando pa'l chesco                               Sacando pa'l chesco",
					fontTiltle);
			Paragraph paragraph2 = new Paragraph(
					"Jornada: " + request.getWeek() + " Fecha Limite:" + request.getDeadline() + "     Jornada: "
							+ request.getWeek() + " Fecha Limite:" + request.getDeadline(),
					fontTiltle);
			paragraph.setAlignment(Paragraph.ALIGN_LEFT);
			paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(paragraph);
			document.add(paragraph2);

			PdfPTable table = new PdfPTable(10);
			table.setWidthPercentage(90f);
			table.setWidths(new int[] { 1, 4, 1, 4, 1, 1, 4, 1, 4, 1 });
			table.setSpacingBefore(5);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);

			// Create Table Cells for table header
			PdfPCell cell = new PdfPCell();
			// Setting the background color and padding
			// cell.setBackgroundColor(CMYKColor.MAGENTA);
			cell.setPadding(3);
			// Creating font
			// Setting font style and size
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setColor(CMYKColor.BLACK);
			// Adding headings in the created table cell/ header
			// Adding Cell to table
			for (MatchesRequest element : request.getMatches()) {

				cell.setPhrase(new Phrase("L", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase(element.getLocal().toUpperCase(), font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("E", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase(element.getVisitor().toUpperCase(), font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("V", font));
				table.addCell(cell);

				cell.setPhrase(new Phrase("L", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase(element.getLocal().toUpperCase(), font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("E", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase(element.getVisitor().toUpperCase(), font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("V", font));
				table.addCell(cell);
			}
			// Adding the created table to document
			document.add(table);
			Paragraph name = new Paragraph("Nombre:________________________ Nombre:________________________",fontTiltle);
			name.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(name);
			
			Paragraph space = new Paragraph(" ",fontTiltle);
			space.setAlignment(Paragraph.ALIGN_LEFT);
			document.add(space);
		}
		// Closing the document
		document.close();
	}
}