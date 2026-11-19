-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2026 a las 07:30:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `login1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detalle_reporte`
--

CREATE TABLE `tb_detalle_reporte` (
  `id_detalle_reporte` int(11) NOT NULL,
  `id_reporte_fk` int(11) DEFAULT NULL,
  `id_elemento_fk` int(11) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_detalle_reporte`
--

INSERT INTO `tb_detalle_reporte` (`id_detalle_reporte`, `id_reporte_fk`, `id_elemento_fk`, `estado`, `cantidad`) VALUES
(33, 40, 23, 'DESHABILITADO', 1),
(34, 41, 24, 'DESHABILITADO', 3),
(35, 42, 25, 'DESHABILITADO', 1),
(36, 43, 26, 'DESHABILITADO', 1),
(37, 44, 27, 'ACTIVADO', 1),
(38, 45, 28, 'ACTIVADO', 1),
(39, 46, 29, 'ACTIVADO', 2),
(40, 47, 30, 'DESHABILITADO', 1),
(41, 48, 31, 'ACTIVADO', 1),
(42, 49, 32, 'ACTIVADO', 1),
(43, 50, 33, 'ACTIVADO', 1),
(44, 51, 34, 'ACTIVADO', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_elementos`
--

CREATE TABLE `tb_elementos` (
  `id_elemento` int(11) NOT NULL,
  `nombre_elemento` varchar(255) DEFAULT NULL,
  `serial` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_elementos`
--

INSERT INTO `tb_elementos` (`id_elemento`, `nombre_elemento`, `serial`, `descripcion`, `marca`) VALUES
(23, 'PORTATIL', 'DSFDSF324', 'NEGRO', 'LENOVO'),
(24, 'TECLADOS', 'DADS324', 'NEGROS', 'LENOVO'),
(25, 'PORTATIL', 'SDF321', 'AZUL CON NEGRO', 'HPUS'),
(26, 'aaaaaaaHHaaFDSaaaa', '1111111111', '11111111111DFGDF1', '111111111'),
(27, 'TABLETA GRAFICA', '423FDS', 'NEGRO CON GRIS', 'INVITUS'),
(28, 'PORTATIL', '5345fdg', 'UNO', 'PERRO'),
(29, 'TECLADO', '4354353', 'NEGROS', 'HP'),
(30, 'TABLETA', '432432', 'BLACK', 'HP'),
(31, 'hgfh', '56gfhgf', 'gfhgf', 'hgfhgfh'),
(32, 'SDSA', '8668', 'DSASD', 'DAS'),
(33, 'PORTATIL', 'FDSBHJK323', 'ROJO', 'ZSUS'),
(34, 'computador', '23213123', 'rojo', 'hp');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_logeo`
--

CREATE TABLE `tb_logeo` (
  `id_login` int(11) NOT NULL,
  `identificacion_fk` int(11) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_logeo`
--

INSERT INTO `tb_logeo` (`id_login`, `identificacion_fk`, `clave`) VALUES
(1, 1095787252, '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_reporte`
--

CREATE TABLE `tb_reporte` (
  `id_reporte` int(11) NOT NULL,
  `fecha_ingreso` timestamp NULL DEFAULT NULL,
  `fecha_salida` timestamp NULL DEFAULT NULL,
  `identificacion_fk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_reporte`
--

INSERT INTO `tb_reporte` (`id_reporte`, `fecha_ingreso`, `fecha_salida`, `identificacion_fk`) VALUES
(40, '2023-11-29 02:22:11', '2023-11-29 03:05:47', 1095787),
(41, '2023-12-03 23:58:30', '2023-12-03 23:59:42', 109546),
(42, '2023-12-05 01:55:31', '2023-12-05 01:55:33', 109525),
(43, '2023-12-05 01:49:21', '2023-12-05 01:49:24', 109515),
(44, '2023-11-29 02:56:03', NULL, 1095787),
(45, '2023-12-03 23:58:53', NULL, 109546),
(46, '2023-12-04 00:42:00', NULL, 109525),
(47, '2023-12-04 00:46:45', '2023-12-05 01:40:22', 109525),
(48, '2023-12-05 01:50:30', NULL, 109515),
(49, '2023-12-05 01:48:23', NULL, 109515),
(50, '2023-12-05 01:57:12', NULL, 109525),
(51, '2026-11-19 04:46:09', NULL, 1234123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `identificacion` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`identificacion`, `nombre`, `apellido`, `correo`, `telefono`, `rol`) VALUES
(109515, 'SIMON', 'PEREZ', 'SIMON@gmail.com', '213124', 'APRENDIZ'),
(109525, 'JUANA', 'BERNAL', 'JUANA@GMAIL.COM', '432432', 'VISITANTE'),
(109546, 'CARLOS', 'FUENTES', 'CARLOS@GMAIL.COM', '3227903', 'APRENDIZ'),
(147852, 'fdfddvdds', 'vvdsvds', 'eewrwe', '45545', 'FUNCIONARIO'),
(248809, 'ewerwg', 'gffdsg', 'gdfgg', '4534', 'FUNCIONARIO'),
(656554, 'dadsad', 'dasd', 'dasdas', '33243232', 'FUNCIONARIO'),
(963852, 'DFSFDS', 'FDSF', 'FDSFDS', '32434', 'FUNCIONARIO'),
(1095787, 'LUISITO', 'COMUNICA', 'LUISILLO@GMAIL.COM', '32132432', 'APRENDIZ'),
(1234123, 'jusdas', 'fddsfdsf', 'dasdss@d.com', '233123213231', 'VISITANTE'),
(4456546, 'gdfgdf', 'gdfg', 'gdfgdg', '345', 'APRENDIZ'),
(5345767, 'fdsf', 'fdsfd', 'fds', '4234', 'VISITANTE'),
(6445654, 'dasdasd', 'eewqeqws', 'dsfdsf', '2432324', 'FUNCIONARIO'),
(6546546, 'ewrewrewrew', 'rewre', 'wewerwre', '343', 'APRENDIZ'),
(345345435, 'gdfgfd', 'dfg', 'gfdg', '32432', 'APRENDIZ'),
(1095787252, 'darid', 'bernal', 'daridbernal@gmail.com', '3185351837', 'APRENDIZ'),
(1099735312, 'JARMINTON', 'RUEDA', 'JAR@GMAIL.COM', '313346689', 'APRENDIZ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_detalle_reporte`
--
ALTER TABLE `tb_detalle_reporte`
  ADD PRIMARY KEY (`id_detalle_reporte`),
  ADD KEY `id_reporte_fk` (`id_reporte_fk`),
  ADD KEY `id_elemento_fk` (`id_elemento_fk`);

--
-- Indices de la tabla `tb_elementos`
--
ALTER TABLE `tb_elementos`
  ADD PRIMARY KEY (`id_elemento`),
  ADD UNIQUE KEY `uk_serial` (`serial`);

--
-- Indices de la tabla `tb_logeo`
--
ALTER TABLE `tb_logeo`
  ADD PRIMARY KEY (`id_login`),
  ADD KEY `identificacion_fk` (`identificacion_fk`);

--
-- Indices de la tabla `tb_reporte`
--
ALTER TABLE `tb_reporte`
  ADD PRIMARY KEY (`id_reporte`),
  ADD KEY `identificacion_fk` (`identificacion_fk`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`identificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_detalle_reporte`
--
ALTER TABLE `tb_detalle_reporte`
  MODIFY `id_detalle_reporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT de la tabla `tb_elementos`
--
ALTER TABLE `tb_elementos`
  MODIFY `id_elemento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `tb_logeo`
--
ALTER TABLE `tb_logeo`
  MODIFY `id_login` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tb_reporte`
--
ALTER TABLE `tb_reporte`
  MODIFY `id_reporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_detalle_reporte`
--
ALTER TABLE `tb_detalle_reporte`
  ADD CONSTRAINT `tb_detalle_reporte_ibfk_1` FOREIGN KEY (`id_reporte_fk`) REFERENCES `tb_reporte` (`id_reporte`),
  ADD CONSTRAINT `tb_detalle_reporte_ibfk_2` FOREIGN KEY (`id_elemento_fk`) REFERENCES `tb_elementos` (`id_elemento`);

--
-- Filtros para la tabla `tb_logeo`
--
ALTER TABLE `tb_logeo`
  ADD CONSTRAINT `tb_logeo_ibfk_1` FOREIGN KEY (`identificacion_fk`) REFERENCES `tb_usuario` (`identificacion`);

--
-- Filtros para la tabla `tb_reporte`
--
ALTER TABLE `tb_reporte`
  ADD CONSTRAINT `tb_reporte_ibfk_1` FOREIGN KEY (`identificacion_fk`) REFERENCES `tb_usuario` (`identificacion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
