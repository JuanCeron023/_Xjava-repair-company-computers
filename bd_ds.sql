-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-07-2020 a las 07:49:22
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_ds`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre_cliente` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mail_cliente` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tel_cliente` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dir_cliente` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ultima_modificacion` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Esta tabla es para registrar clientes';

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre_cliente`, `mail_cliente`, `tel_cliente`, `dir_cliente`, `ultima_modificacion`) VALUES
(1, 'roberto', 'roberto@gmail.com', '3453', 'calle 1', 'juan_capturista'),
(2, 'jorge', 'jorge@gmail.com', '102345', 'calle 1', 'pedro'),
(3, 'eduardo', 'eduardo@gmail.com', '546456', 'calle 3', 'ernesto'),
(4, 'fernando', 'fernando@gmail.com', '123456', 'calle 3', 'ernesto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipos`
--

CREATE TABLE `equipos` (
  `id_equipo` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `tipo_equipo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `marca` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `modelo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `num_serie` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dia_ingreso` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `mes_ingreso` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `annio_ingreso` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `observaciones` longtext COLLATE utf8_unicode_ci NOT NULL,
  `estatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ultima_modificacion` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `comentarios_tecnicos` longtext COLLATE utf8_unicode_ci NOT NULL,
  `revision_tecnica` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Esta tabla es para registrar equipos';

--
-- Volcado de datos para la tabla `equipos`
--

INSERT INTO `equipos` (`id_equipo`, `id_cliente`, `tipo_equipo`, `marca`, `modelo`, `num_serie`, `dia_ingreso`, `mes_ingreso`, `annio_ingreso`, `observaciones`, `estatus`, `ultima_modificacion`, `comentarios_tecnicos`, `revision_tecnica`) VALUES
(6, 3, 'Laptop', 'Acer', 'unodos', '123123', '10', '6', '2020', 'nuevo', 'Nuevo ingreso', 'ernesto', 'ninguno', 'gerardo_tecnico'),
(9, 2, 'Laptop', 'Acer', 'uno', '45345', '10', '6', '2020', 'nuevo', 'Reparado', '', 'reparado', 'ernesto'),
(10, 1, 'Impresora', 'Acer', 'dos', '345345', '10', '6', '2020', 'uno', 'Reparado', 'ernesto', 'ninguno', 'ernesto'),
(15, 4, 'Laptop', 'Acer', 'tres', '456456', '10', '6', '2020', 'ninguno', 'Nuevo ingreso', '', 'ninguno', ''),
(23, 4, 'Laptop', 'Acer', 'tres', '34534', '10', '6', '2020', 'ninguno', 'Nuevo ingreso', '', 'ninguno', ''),
(27, 1, 'Laptop', 'Acer', 'tres', 'ty456456', '10', '6', '2020', 'ninguno', 'Nuevo ingreso', '', 'ninguno', ''),
(29, 3, 'Laptop', 'Acer', 'dos', '534534', '10', '6', '2020', 'ninguno', 'Nuevo ingreso', '', 'ninguno', ''),
(43, 1, 'Laptop', 'Acer', 'uno', '0', '11', '6', '2020', 'ninguno', 'No reparado', 'juan_capturista', 'ninguno', 'gerardo_tecnico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tipo_nivel` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `estatus` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `registrado_por` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Esta tabla es para el registro de usuarios';

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `email`, `telefono`, `username`, `password`, `tipo_nivel`, `estatus`, `registrado_por`) VALUES
(1, 'Ernesto perez', 'ernesto@ejemplo.com', '12345678999', 'ernesto', '123456', 'Administrador', 'Activo', 'ernesto'),
(2, 'Juan Torres', 'juan@ejemplo.com', '123456', 'juan_capturista', '123456', 'Capturista', 'Activo', 'ernesto'),
(3, 'Gerardo Tellez', 'gerardo@ejemplo.com', '1234567890', 'gerardo_tecnico', '123456', 'Tecnico', 'Activo', 'ernesto'),
(4, 'pedroe', 'pedro@hotmail.com', '111111', 'pedro', '1', 'Administrador', 'Activo', 'ernesto');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `equipos`
--
ALTER TABLE `equipos`
  ADD PRIMARY KEY (`id_equipo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `equipos`
--
ALTER TABLE `equipos`
  MODIFY `id_equipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
