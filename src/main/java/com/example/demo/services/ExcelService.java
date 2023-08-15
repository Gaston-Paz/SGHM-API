package com.example.demo.services;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.models.Paciente;

@Service
public class ExcelService {

    @Autowired
    PacienteService _PacienteService;

    public ResponseEntity<byte[]> ExportarHistoria() throws IOException {
        Optional<Paciente> paciente = _PacienteService.obtenerPorId((long) 1);

        FileInputStream template = new FileInputStream(
                "C:\\Users\\Gastón\\Documents\\GitHub\\SGHM-API\\src\\main\\resources\\Template-Historia.xlsx");
        Workbook workbook = new XSSFWorkbook(template);
        template.close();

        String nombreArchivo = paciente.get().getNombre() + ".xlsx";
        Font font = workbook.createFont();
        font.setBold(true);

        XSSFColor fondoVerdeColor = new XSSFColor(new java.awt.Color(184, 223, 184), null);
        CellStyle fondoVerde = workbook.createCellStyle();
        ((XSSFCellStyle) fondoVerde).setFillForegroundColor(fondoVerdeColor);
        fondoVerde.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        fondoVerde.setFont(font);
        fondoVerde.setAlignment(HorizontalAlignment.LEFT);

        CellStyle formatoFecha = workbook.createCellStyle();
        formatoFecha.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));
        ((XSSFCellStyle) formatoFecha).setFillForegroundColor(fondoVerdeColor);
        formatoFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        formatoFecha.setFont(font);
        formatoFecha.setAlignment(HorizontalAlignment.LEFT);

        CellStyle bordeTopMasFondo = workbook.createCellStyle();
        ((XSSFCellStyle) bordeTopMasFondo).setFillForegroundColor(fondoVerdeColor);
        bordeTopMasFondo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        bordeTopMasFondo.setBorderTop(BorderStyle.MEDIUM);
        bordeTopMasFondo.setTopBorderColor(IndexedColors.BLACK.getIndex());
        bordeTopMasFondo.setFont(font);
        bordeTopMasFondo.setAlignment(HorizontalAlignment.LEFT);

        CellStyle bordeBottomMasFondo = workbook.createCellStyle();
        ((XSSFCellStyle) bordeBottomMasFondo).setFillForegroundColor(fondoVerdeColor);
        bordeBottomMasFondo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        bordeBottomMasFondo.setBorderBottom(BorderStyle.MEDIUM);
        bordeBottomMasFondo.setTopBorderColor(IndexedColors.BLACK.getIndex());
        bordeBottomMasFondo.setFont(font);
        bordeBottomMasFondo.setAlignment(HorizontalAlignment.LEFT);

        Sheet datosPersonales = workbook.getSheet("Datos Personales");
        datosPersonales = EscribirDatosPersonales(datosPersonales, paciente.get(), formatoFecha, fondoVerde,
                bordeBottomMasFondo, bordeTopMasFondo);
        datosPersonales.autoSizeColumn(1, true);
        datosPersonales.autoSizeColumn(2, true);
        datosPersonales.autoSizeColumn(3, true);
        datosPersonales.autoSizeColumn(4, true);
        datosPersonales.autoSizeColumn(5, true);

        int pictureIdx = workbook.addPicture(paciente.get().getFotoPerfil(), Workbook.PICTURE_TYPE_JPEG);
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = datosPersonales.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
        anchor.setRow1(5);
        anchor.setRow2(18);
        anchor.setCol1(7);
        anchor.setCol2(10);

        Picture picture = drawing.createPicture(anchor, pictureIdx);
        // picture.resize();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", nombreArchivo);

        workbook.close();

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    public Sheet EscribirDatosPersonales(Sheet sheet, Paciente paciente, CellStyle formatoFecha, CellStyle fondoVerde,
            CellStyle bordeBottom, CellStyle bordeTop) {

        Row rowNombre = sheet.getRow(6);
        Cell cellNombreValor = rowNombre.createCell(3);
        cellNombreValor.setCellValue(paciente.getNombre());
        cellNombreValor.setCellStyle(bordeTop);

        Row rowApellido = sheet.getRow(7);
        Cell cellApellidoValor = rowApellido.createCell(3);
        cellApellidoValor.setCellValue(paciente.getApellido());
        cellApellidoValor.setCellStyle(fondoVerde);

        Row rowFecha = sheet.getRow(8);
        Cell cellFechaValor = rowFecha.createCell(3);
        cellFechaValor.setCellValue(paciente.getFechaNacimiento());
        cellFechaValor.setCellStyle(formatoFecha);

        Row rowNacio = sheet.getRow(9);
        Cell cellNacioValor = rowNacio.createCell(3);
        cellNacioValor.setCellValue(paciente.getNacio());
        cellNacioValor.setCellStyle(fondoVerde);

        Row rowOcupacion = sheet.getRow(10);
        Cell cellOcupacionValor = rowOcupacion.createCell(3);
        cellOcupacionValor.setCellValue(paciente.getOcupacion());
        cellOcupacionValor.setCellStyle(fondoVerde);

        Row rowLocalidad = sheet.getRow(11);
        Cell cellLocalidadValor = rowLocalidad.createCell(3);
        cellLocalidadValor.setCellValue(paciente.getLocalidad());
        cellLocalidadValor.setCellStyle(fondoVerde);

        Row rowParte = sheet.getRow(12);
        Cell cellParteValor = rowParte.createCell(3);
        cellParteValor.setCellValue(paciente.getDeParte());
        cellParteValor.setCellStyle(fondoVerde);

        Row rowEmail = sheet.getRow(13);
        Cell cellEmailValor = rowEmail.createCell(3);
        cellEmailValor.setCellValue(paciente.getEmail());
        cellEmailValor.setCellStyle(fondoVerde);

        Row rowTelefono = sheet.getRow(14);
        Cell cellTelefonoValor = rowTelefono.createCell(3);
        cellTelefonoValor.setCellValue(paciente.getCelular());
        cellTelefonoValor.setCellStyle(fondoVerde);

        Row rowOtros = sheet.getRow(15);
        Cell cellOtrosValor = rowOtros.createCell(3);
        cellOtrosValor.setCellValue(paciente.getOtros());
        cellOtrosValor.setCellStyle(bordeBottom);

        return sheet;
    }

}
