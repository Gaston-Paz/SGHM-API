package com.example.demo.services;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.example.demo.models.Antecedente;
import com.example.demo.models.ConsultaInicial;
import com.example.demo.models.Paciente;
import com.example.demo.models.Tratamiento;

@Service
public class ExcelService {

        @Autowired
        PacienteService _PacienteService;
        @Autowired
        ConsultaInicialService _ConsultaInicialService;
        @Autowired
        AntecedenteService _AntecedenteService;
        @Autowired
        TratamientoService _TratamientoService;

        public ResponseEntity<byte[]> ExportarHistoria(long idPaciente) throws IOException {
                Optional<Paciente> paciente = _PacienteService.obtenerPorId(idPaciente);

                FileInputStream template = new FileInputStream(
                                "C:\\Users\\Gastón\\Documents\\GitHub\\SGHM-API\\src\\main\\resources\\Template-Historia.xlsx");
                Workbook workbook = new XSSFWorkbook(template);
                template.close();

                String nombreArchivo = paciente.get().getNombre() + ".xlsx";
                Font font = workbook.createFont();
                font.setBold(true);

                XSSFColor fondoVerdeColor = new XSSFColor(new java.awt.Color(184, 223, 184), null);
                XSSFColor fondoAmarilloColor = new XSSFColor(new java.awt.Color(255, 230, 153), null);
                XSSFColor fondoNaranjaColor = new XSSFColor(new java.awt.Color(248, 203, 173), null);
                XSSFColor fondoRojoColor = new XSSFColor(new java.awt.Color(244, 120, 120), null);

                Sheet datosPersonales = workbook.getSheet("Datos Personales");
                datosPersonales = EscribirDatosPersonales(datosPersonales, paciente.get(), workbook, font,
                                fondoVerdeColor);

                Sheet consultaInicial = workbook.getSheet("Consulta Inicial");
                ConsultaInicial consulta = _ConsultaInicialService
                                .obtenerPorId(paciente.get().getConsultaInicial().getIdConsulta());
                consultaInicial = EscribirConsultaInicial(consultaInicial, consulta, workbook, font,
                                fondoAmarilloColor);

                Sheet antecedentes = workbook.getSheet("Antecedentes");
                Antecedente antecedente = _AntecedenteService
                                .obtenerPorId(paciente.get().getAntecedente().getIdAntecedente());
                antecedentes = EscribirAntecedentes(antecedentes, antecedente, workbook, font,
                                fondoNaranjaColor);

                Sheet tratamientos = workbook.getSheet("Tratamientos");
                ArrayList<Tratamiento> tratamiento = _TratamientoService
                                .obtenerPaciente(paciente.get());
                antecedentes = EscribirTratamientos(tratamientos, tratamiento, workbook, font,
                                fondoRojoColor);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(
                                MediaType.parseMediaType(
                                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.setContentDispositionFormData("attachment", nombreArchivo);

                workbook.close();

                return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        }

        public Sheet EscribirDatosPersonales(Sheet sheet, Paciente paciente, Workbook workbook, Font font,
                        XSSFColor fondoVerdeColor) {

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

                Row rowNombre = sheet.getRow(6);
                Cell cellNombreValor = rowNombre.createCell(3);
                cellNombreValor.setCellValue(paciente.getNombre());
                cellNombreValor.setCellStyle(bordeTopMasFondo);

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
                cellOtrosValor.setCellStyle(bordeBottomMasFondo);

                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);

                int pictureIdx = workbook.addPicture(paciente.getFotoPerfil(), Workbook.PICTURE_TYPE_JPEG);
                CreationHelper helper = workbook.getCreationHelper();
                Drawing drawing = sheet.createDrawingPatriarch();

                ClientAnchor anchor = helper.createClientAnchor();
                anchor.setAnchorType(AnchorType.MOVE_AND_RESIZE);
                anchor.setRow1(5);
                anchor.setRow2(18);
                anchor.setCol1(7);
                anchor.setCol2(10);

                Picture picture = drawing.createPicture(anchor, pictureIdx);

                return sheet;
        }

        public Sheet EscribirConsultaInicial(Sheet sheet, ConsultaInicial consulta, Workbook workbook, Font font,
                        XSSFColor fondoAmarilloColor) {
                CellStyle formatoFecha = workbook.createCellStyle();
                formatoFecha.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));
                ((XSSFCellStyle) formatoFecha).setFillForegroundColor(fondoAmarilloColor);
                formatoFecha.setFont(font);
                formatoFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                formatoFecha.setBorderTop(BorderStyle.MEDIUM);
                formatoFecha.setTopBorderColor(IndexedColors.BLACK.getIndex());
                formatoFecha.setAlignment(HorizontalAlignment.LEFT);

                CellStyle formatoFechaSinBorde = workbook.createCellStyle();
                formatoFechaSinBorde
                                .setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));
                ((XSSFCellStyle) formatoFechaSinBorde).setFillForegroundColor(fondoAmarilloColor);
                formatoFechaSinBorde.setFont(font);
                formatoFechaSinBorde.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                formatoFechaSinBorde.setAlignment(HorizontalAlignment.LEFT);

                CellStyle fondoAmarillo = workbook.createCellStyle();
                ((XSSFCellStyle) fondoAmarillo).setFillForegroundColor(fondoAmarilloColor);
                fondoAmarillo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoAmarillo.setFont(font);
                fondoAmarillo.setAlignment(HorizontalAlignment.LEFT);

                CellStyle fondoAmarilloBordeBottom = workbook.createCellStyle();
                ((XSSFCellStyle) fondoAmarilloBordeBottom).setFillForegroundColor(fondoAmarilloColor);
                fondoAmarilloBordeBottom.setFont(font);
                fondoAmarilloBordeBottom.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoAmarilloBordeBottom.setBorderBottom(BorderStyle.MEDIUM);
                fondoAmarilloBordeBottom.setTopBorderColor(IndexedColors.BLACK.getIndex());

                Row rowFecha = sheet.getRow(6);
                Cell cellFecha = rowFecha.createCell(3);
                cellFecha.setCellValue(consulta.getFecha());
                cellFecha.setCellStyle(formatoFecha);

                Row rowMotivo = sheet.getRow(7);
                Cell cellMotivo = rowMotivo.createCell(3);
                cellMotivo.setCellValue(consulta.getMotivo());
                cellMotivo.setCellStyle(fondoAmarillo);

                Row rowActividad = sheet.getRow(8);
                Cell cellActividad = rowActividad.createCell(3);
                cellActividad.setCellValue(
                                consulta.getActividadFisica() != null ? consulta.getActividadFisica() : "-");
                cellActividad.setCellStyle(fondoAmarillo);

                Row rowAntiguedad = sheet.getRow(9);
                Cell cellAntiguedad = rowAntiguedad.createCell(3);
                cellAntiguedad.setCellValue(consulta.getAntiguedad() != null ? consulta.getAntiguedad() : "-");
                cellAntiguedad.setCellStyle(fondoAmarillo);

                Row rowLocalizacion = sheet.getRow(10);
                Cell cellLocalizacion = rowLocalizacion.createCell(3);
                cellLocalizacion.setCellValue(
                                consulta.getLocalizacion() != null ? consulta.getLocalizacion() : "-");
                cellLocalizacion.setCellStyle(fondoAmarillo);

                Row rowIntensidad = sheet.getRow(11);
                Cell cellIntensidad = rowIntensidad.createCell(3);
                cellIntensidad.setCellValue(consulta.getIntensidad() != null ? consulta.getIntensidad() : "-");
                cellIntensidad.setCellStyle(fondoAmarillo);

                Row rowCaracteristica = sheet.getRow(12);
                Cell cellCaracteristica = rowCaracteristica.createCell(3);
                cellCaracteristica.setCellValue(
                                consulta.getCaracteristica() != null ? consulta.getCaracteristica() : "-");
                cellCaracteristica.setCellStyle(fondoAmarillo);

                Row rowIrradiacion = sheet.getRow(13);
                Cell cellIrradiacion = rowIrradiacion.createCell(3);
                cellIrradiacion.setCellValue(consulta.getIrradiacion() != null ? consulta.getIrradiacion() : "-");
                cellIrradiacion.setCellStyle(fondoAmarillo);

                Row rowAtenua = sheet.getRow(14);
                Cell cellAtenua = rowAtenua.createCell(3);
                cellAtenua.setCellValue(consulta.getAtenua() != null ? consulta.getAtenua() : "-");
                cellAtenua.setCellStyle(fondoAmarillo);

                Row rowCovid = sheet.getRow(15);
                Cell cellCovid = rowCovid.createCell(3);
                cellCovid.setCellValue(consulta.getCovid() ? "Si" : "No");
                cellCovid.setCellStyle(fondoAmarillo);

                Row rowFechaCovid = sheet.getRow(16);
                Cell cellFechaCovid = rowFechaCovid.createCell(3);
                if (consulta.getCovid()) {
                        cellFechaCovid.setCellValue(consulta.getFechaCovid());
                        cellFechaCovid.setCellStyle(formatoFechaSinBorde);
                } else {
                        cellFechaCovid.setCellValue("-");
                        cellFechaCovid.setCellStyle(fondoAmarillo);
                }

                Row rowOtros = sheet.getRow(17);
                Cell cellOtros = rowOtros.createCell(3);
                cellOtros.setCellValue(consulta.getOtros() != null ? consulta.getOtros() : "-");
                cellOtros.setCellStyle(fondoAmarilloBordeBottom);

                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);

                return sheet;
        }

        public Sheet EscribirAntecedentes(Sheet sheet, Antecedente antecedente, Workbook workbook, Font font,
                        XSSFColor fondoNaranjaColor) {

                CellStyle fondoNaranja = workbook.createCellStyle();
                ((XSSFCellStyle) fondoNaranja).setFillForegroundColor(fondoNaranjaColor);
                fondoNaranja.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoNaranja.setFont(font);
                fondoNaranja.setAlignment(HorizontalAlignment.LEFT);

                CellStyle fondoNaranjaBordeBottom = workbook.createCellStyle();
                ((XSSFCellStyle) fondoNaranjaBordeBottom).setFillForegroundColor(fondoNaranjaColor);
                fondoNaranjaBordeBottom.setFont(font);
                fondoNaranjaBordeBottom.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoNaranjaBordeBottom.setBorderBottom(BorderStyle.MEDIUM);
                fondoNaranjaBordeBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());

                CellStyle fondoNaranjaBordeTop = workbook.createCellStyle();
                ((XSSFCellStyle) fondoNaranjaBordeTop).setFillForegroundColor(fondoNaranjaColor);
                fondoNaranjaBordeTop.setFont(font);
                fondoNaranjaBordeTop.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoNaranjaBordeTop.setBorderTop(BorderStyle.MEDIUM);
                fondoNaranjaBordeTop.setTopBorderColor(IndexedColors.BLACK.getIndex());

                CellStyle fondoNaranjaBordeTopBottom = workbook.createCellStyle();
                ((XSSFCellStyle) fondoNaranjaBordeTopBottom).setFillForegroundColor(fondoNaranjaColor);
                fondoNaranjaBordeTopBottom.setFont(font);
                fondoNaranjaBordeTopBottom.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                fondoNaranjaBordeTopBottom.setBorderTop(BorderStyle.MEDIUM);
                fondoNaranjaBordeTopBottom.setTopBorderColor(IndexedColors.BLACK.getIndex());
                fondoNaranjaBordeTopBottom.setBorderBottom(BorderStyle.MEDIUM);
                fondoNaranjaBordeTopBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());

                Row rowCirugia = sheet.getRow(6);
                Cell cellCirugia = rowCirugia.createCell(3);
                cellCirugia.setCellValue(antecedente.getCirugias() != null ? antecedente.getCirugias() : "-");
                cellCirugia.setCellStyle(fondoNaranjaBordeTopBottom);

                Row rowImplantesSup = sheet.getRow(9);
                Cell cellImplantesSup = rowImplantesSup.createCell(3);
                cellImplantesSup.setCellValue(
                                antecedente.getImplanteSuperior() != null ? antecedente.getImplanteSuperior() : "-");
                cellImplantesSup.setCellStyle(fondoNaranjaBordeTop);

                Row rowImplantesInf = sheet.getRow(10);
                Cell cellImplantesInf = rowImplantesInf.createCell(3);
                cellImplantesInf.setCellValue(
                                antecedente.getImplanteInferior() != null ? antecedente.getImplanteInferior() : "-");
                cellImplantesInf.setCellStyle(fondoNaranja);

                Row rowFaltanteSup = sheet.getRow(11);
                Cell cellFaltanteSup = rowFaltanteSup.createCell(3);
                cellFaltanteSup.setCellValue(
                                antecedente.getPiezasFaltantesSup() != null ? antecedente.getPiezasFaltantesSup()
                                                : "-");
                cellFaltanteSup.setCellStyle(fondoNaranja);

                Row rowFaltanteInf = sheet.getRow(12);
                Cell cellFaltanteInf = rowFaltanteInf.createCell(3);
                cellFaltanteInf.setCellValue(
                                antecedente.getPiezasFaltantesInf() != null ? antecedente.getPiezasFaltantesInf()
                                                : "-");
                cellFaltanteInf.setCellStyle(fondoNaranja);

                Row rowProtesis = sheet.getRow(13);
                Cell cellProtesis = rowProtesis.createCell(3);
                cellProtesis.setCellValue(antecedente.getProtesis() != null ? antecedente.getProtesis() : "-");
                cellProtesis.setCellStyle(fondoNaranja);

                Row rowPlaca = sheet.getRow(14);
                Cell cellPlaca = rowPlaca.createCell(3);
                cellPlaca.setCellValue(antecedente.getContencion() ? "Si" : "-¿No");
                cellPlaca.setCellStyle(fondoNaranja);

                Row rowPlacaDescanso = sheet.getRow(15);
                Cell cellPlacaDescanso = rowPlacaDescanso.createCell(3);
                cellPlacaDescanso.setCellValue(antecedente.getPlacaDescanso() ? "Si" : "-¿No");
                cellPlacaDescanso.setCellStyle(fondoNaranja);

                Row rowOrtodoncia = sheet.getRow(16);
                Cell cellOrtodoncia = rowOrtodoncia.createCell(3);
                cellOrtodoncia.setCellValue(antecedente.getOrtodoncia() ? "Si" : "-¿No");
                cellOrtodoncia.setCellStyle(fondoNaranja);

                if (antecedente.getOrtodoncia()) {
                        Row rowEdad = sheet.getRow(17);
                        Cell cellEdad = rowEdad.createCell(3);
                        cellEdad.setCellValue(antecedente.getEdadOrtodoncia());
                        cellEdad.setCellStyle(fondoNaranjaBordeBottom);
                } else {
                        Row rowEdad = sheet.getRow(17);
                        Cell cellEdad = rowEdad.createCell(3);
                        cellEdad.setCellValue("-");
                        cellEdad.setCellStyle(fondoNaranjaBordeBottom);
                }
                Row rowMenstruacion = sheet.getRow(20);
                Cell cellMenstruacion = rowMenstruacion.createCell(3);
                cellMenstruacion.setCellValue(antecedente.getMenstruacion() ? "Si" : "-¿No");
                cellMenstruacion.setCellStyle(fondoNaranjaBordeTop);

                Row rowFrecuencia = sheet.getRow(21);
                Cell cellFrecuencia = rowFrecuencia.createCell(3);
                cellFrecuencia.setCellValue(antecedente.getFrecuencia() != null ? antecedente.getFrecuencia() : "-");
                cellFrecuencia.setCellStyle(fondoNaranja);

                Row rowDuracion = sheet.getRow(22);
                Cell cellDuracion = rowDuracion.createCell(3);
                cellDuracion.setCellValue(antecedente.getDuracion() != null ? antecedente.getDuracion() : "-");
                cellDuracion.setCellStyle(fondoNaranja);

                Row rowVolumen = sheet.getRow(23);
                Cell cellVolumen = rowVolumen.createCell(3);
                cellVolumen.setCellValue(antecedente.getVolumen() != null ? antecedente.getVolumen() : "-");
                cellVolumen.setCellStyle(fondoNaranja);

                Row rowEmbarazos = sheet.getRow(24);
                Cell cellEmbarazos = rowEmbarazos.createCell(3);
                cellEmbarazos.setCellValue(antecedente.getEmbarazos() ? "Si" : "-¿No");
                cellEmbarazos.setCellStyle(fondoNaranja);

                Row rowPartos = sheet.getRow(25);
                Cell cellPartos = rowPartos.createCell(3);
                cellPartos.setCellValue(antecedente.getPartos() != null ? antecedente.getPartos() : "-");
                cellPartos.setCellStyle(fondoNaranja);

                Row rowAbortosEsp = sheet.getRow(26);
                Cell cellAbortosEsp = rowAbortosEsp.createCell(3);
                cellAbortosEsp.setCellValue(
                                antecedente.getAbortosEspontaneo() != null ? antecedente.getAbortosEspontaneo() : "-");
                cellAbortosEsp.setCellStyle(fondoNaranja);

                Row rowAbortosInd = sheet.getRow(27);
                Cell cellAbortosInd = rowAbortosInd.createCell(3);
                cellAbortosInd.setCellValue(
                                antecedente.getAbortosInducido() != null ? antecedente.getAbortosInducido() : "-");
                cellAbortosInd.setCellStyle(fondoNaranjaBordeBottom);

                Row rowIntestinal = sheet.getRow(30);
                Cell cellIntestinal = rowIntestinal.createCell(3);
                cellIntestinal.setCellValue(antecedente.getIntestinal() != null ? antecedente.getIntestinal() : "-");
                cellIntestinal.setCellStyle(fondoNaranjaBordeTop);

                Row rowDigestivo = sheet.getRow(31);
                Cell cellDigestivo = rowDigestivo.createCell(3);
                cellDigestivo.setCellValue(antecedente.getDigestivo() != null ? antecedente.getDigestivo() : "-");
                cellDigestivo.setCellStyle(fondoNaranja);

                Row rowCardiaco = sheet.getRow(32);
                Cell cellCardiaco = rowCardiaco.createCell(3);
                cellCardiaco.setCellValue(antecedente.getCardiaco() != null ? antecedente.getCardiaco() : "-");
                cellCardiaco.setCellStyle(fondoNaranja);

                Row rowUrogenital = sheet.getRow(33);
                Cell cellUrogenital = rowUrogenital.createCell(3);
                cellUrogenital.setCellValue(antecedente.getUrogenital() != null ? antecedente.getUrogenital() : "-");
                cellUrogenital.setCellStyle(fondoNaranja);

                Row rowOseo = sheet.getRow(34);
                Cell cellOseo = rowOseo.createCell(3);
                cellOseo.setCellValue(antecedente.getOseo() != null ? antecedente.getOseo() : "-");
                cellOseo.setCellStyle(fondoNaranja);

                Row rowFuma = sheet.getRow(35);
                Cell cellFuma = rowFuma.createCell(3);
                cellFuma.setCellValue(antecedente.getFuma() != null ? antecedente.getFuma() : "-");
                cellFuma.setCellStyle(fondoNaranja);

                Row rowOtrasDrogas = sheet.getRow(36);
                Cell cellOtrasDrogas = rowOtrasDrogas.createCell(3);
                cellOtrasDrogas.setCellValue(antecedente.getOtrasDrogas() != null ? antecedente.getOtrasDrogas() : "-");
                cellOtrasDrogas.setCellStyle(fondoNaranjaBordeBottom);

                Row rowAccidentes = sheet.getRow(39);
                Cell cellAccidentes = rowAccidentes.createCell(3);
                cellAccidentes.setCellValue(antecedente.getAccidentes() != null ? antecedente.getAccidentes() : "-");
                cellAccidentes.setCellStyle(fondoNaranjaBordeTop);

                Row rowMedicacion = sheet.getRow(40);
                Cell cellMedicacion = rowMedicacion.createCell(3);
                cellMedicacion.setCellValue(antecedente.getMedicacion() != null ? antecedente.getMedicacion() : "-");
                cellMedicacion.setCellStyle(fondoNaranja);

                Row rowDiabetes = sheet.getRow(41);
                Cell cellDiabetes = rowDiabetes.createCell(3);
                cellDiabetes.setCellValue(antecedente.getDiabetes() ? "Si" : "No");
                cellDiabetes.setCellStyle(fondoNaranja);

                Row rowFracturas = sheet.getRow(42);
                Cell cellFracturas = rowFracturas.createCell(3);
                cellFracturas.setCellValue(antecedente.getFracturas() != null ? antecedente.getFracturas() : "-");
                cellFracturas.setCellStyle(fondoNaranja);

                Row rowDolorCabeza = sheet.getRow(43);
                Cell cellDolorCabeza = rowDolorCabeza.createCell(3);
                cellDolorCabeza.setCellValue(antecedente.getDolorCabeza() != null ? antecedente.getDolorCabeza() : "-");
                cellDolorCabeza.setCellStyle(fondoNaranja);

                Row rowTiroides = sheet.getRow(44);
                Cell cellTiroides = rowTiroides.createCell(3);
                cellTiroides.setCellValue(antecedente.getTiroides() != null ? antecedente.getTiroides() : "-");
                cellTiroides.setCellStyle(fondoNaranja);

                Row rowOtros = sheet.getRow(45);
                Cell cellOtros = rowOtros.createCell(3);
                cellOtros.setCellValue(antecedente.getOtros() != null ? antecedente.getOtros() : "-");
                cellOtros.setCellStyle(fondoNaranja);

                Row rowAlimentacion = sheet.getRow(46);
                Cell cellAlimentacion = rowAlimentacion.createCell(3);
                cellAlimentacion.setCellValue(
                                antecedente.getAlimentacion() != null ? antecedente.getAlimentacion() : "-");
                cellAlimentacion.setCellStyle(fondoNaranja);

                Row rowPerdidas = sheet.getRow(47);
                Cell cellPerdidas = rowPerdidas.createCell(3);
                cellPerdidas.setCellValue(antecedente.getPerdidas() != null ? antecedente.getPerdidas() : "-");
                cellPerdidas.setCellStyle(fondoNaranjaBordeBottom);

                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);

                return sheet;
        }

        public Sheet EscribirTratamientos(Sheet sheet, ArrayList<Tratamiento> tratamientos, Workbook workbook,
                        Font font,
                        XSSFColor fondoRojoColor) {

                CellStyle estilo = workbook.createCellStyle();
                ((XSSFCellStyle) estilo).setFillForegroundColor(fondoRojoColor);
                estilo.setFont(font);
                estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                estilo.setBorderTop(BorderStyle.MEDIUM);
                estilo.setTopBorderColor(IndexedColors.BLACK.getIndex());
                estilo.setBorderBottom(BorderStyle.MEDIUM);
                estilo.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                estilo.setBorderLeft(BorderStyle.MEDIUM);
                estilo.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                estilo.setBorderRight(BorderStyle.MEDIUM);
                estilo.setRightBorderColor(IndexedColors.BLACK.getIndex());
                estilo.setAlignment(HorizontalAlignment.CENTER);

                CellStyle estiloFecha = workbook.createCellStyle();
                ((XSSFCellStyle) estiloFecha).setFillForegroundColor(fondoRojoColor);
                estiloFecha.setFont(font);
                estiloFecha.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                estiloFecha.setBorderTop(BorderStyle.MEDIUM);
                estiloFecha.setTopBorderColor(IndexedColors.BLACK.getIndex());
                estiloFecha.setBorderBottom(BorderStyle.MEDIUM);
                estiloFecha.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                estiloFecha.setBorderLeft(BorderStyle.MEDIUM);
                estiloFecha.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                estiloFecha.setBorderRight(BorderStyle.MEDIUM);
                estiloFecha.setRightBorderColor(IndexedColors.BLACK.getIndex());
                estiloFecha.setAlignment(HorizontalAlignment.CENTER);
                estiloFecha.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("dd-MM-yyyy"));

                Iterator<Tratamiento> obj = tratamientos.iterator();
                int fila = 7;
                while (obj.hasNext()) {
                        Tratamiento aux = obj.next();
                        Row row = sheet.createRow(fila);
                        for (int x = 1; x <= 10; x++) {

                                Cell cellrow = row.createCell(x);
                                switch (x) {
                                        case 1:
                                                cellrow.setCellValue(aux.getFecha());
                                                break;
                                        case 2:
                                                cellrow.setCellValue(aux.getMotivo());
                                                break;
                                        case 3:
                                                cellrow.setCellValue(aux.getTrianguloDeTalla() != null
                                                                && aux.getTrianguloDeTalla().trim().length() > 0
                                                                                ? aux.getTrianguloDeTalla()
                                                                                : "-");
                                                break;
                                        case 4:
                                                cellrow.setCellValue(
                                                                aux.getBarral() != null
                                                                                && aux.getBarral().trim().length() > 0
                                                                                                ? aux.getBarral()
                                                                                                : "-");
                                                break;
                                        case 5:
                                                cellrow.setCellValue(aux.getAlturaDeIliacos() != null
                                                                && aux.getAlturaDeIliacos().trim().length() > 0
                                                                                ? aux.getAlturaDeIliacos()
                                                                                : "-");
                                                break;
                                        case 6:
                                                cellrow.setCellValue(aux.getEspecifico() != null
                                                                && aux.getEspecifico().trim().length() > 0
                                                                                ? aux.getEspecifico()
                                                                                : "-");
                                                break;
                                        case 7:
                                                cellrow.setCellValue(
                                                                aux.getEsferas() != null
                                                                                && aux.getEsferas().trim().length() > 0
                                                                                                ? aux.getEsferas()
                                                                                                : "-");
                                                break;
                                        case 8:
                                                cellrow.setCellValue(aux.getSedestacion());
                                                break;
                                        case 9:
                                                cellrow.setCellValue(aux.getSugerencias() != null
                                                                && aux.getSugerencias().trim().length() > 0
                                                                                ? aux.getSugerencias()
                                                                                : "-");
                                                break;

                                }

                                if (x == 10 && aux.getProximoTurnoIndicado() != null) {
                                        cellrow.setCellValue(aux.getProximoTurnoIndicado());
                                        cellrow.setCellStyle(estiloFecha);
                                } else if (x == 10) {
                                        cellrow.setCellValue("-");
                                        cellrow.setCellStyle(estilo);
                                } else if (x == 1)
                                        cellrow.setCellStyle(estiloFecha);
                                else
                                        cellrow.setCellStyle(estilo);

                        }
                        fila++;
                }

                sheet.autoSizeColumn(1, true);
                sheet.autoSizeColumn(2, true);
                sheet.autoSizeColumn(3, true);
                sheet.autoSizeColumn(4, true);
                sheet.autoSizeColumn(5, true);
                sheet.autoSizeColumn(6, true);
                sheet.autoSizeColumn(7, true);
                sheet.autoSizeColumn(8, true);
                sheet.autoSizeColumn(9, true);
                sheet.autoSizeColumn(10, true);
                sheet.autoSizeColumn(11, true);

                return sheet;
        }
}
