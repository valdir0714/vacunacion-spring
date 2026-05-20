-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2026 a las 17:21:47
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
-- Base de datos: `vacunacion_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log_cambios`
--

CREATE TABLE `log_cambios` (
  `id` int(11) NOT NULL,
  `accion` enum('INSERT','UPDATE','DELETE') NOT NULL,
  `id_registro` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `documento` varchar(20) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id`, `nombres`, `apellidos`, `documento`, `fecha_nacimiento`, `created_at`) VALUES
(1, 'Juan', 'Pérez', '1234567890', '1985-06-15', '2026-04-23 14:35:29'),
(2, 'María', 'González', '0987654321', '1990-03-22', '2026-04-23 14:35:29'),
(3, 'Carlos', 'Rodríguez', '2345678901', '1978-11-05', '2026-04-23 14:35:29'),
(4, 'Ana', 'Martínez', '3456789012', '1995-09-30', '2026-04-23 14:35:29'),
(5, 'Pedro', 'Sánchez', '4567890123', '1980-02-18', '2026-04-23 14:35:29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros_vacunacion`
--

CREATE TABLE `registros_vacunacion` (
  `id` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `id_vacuna` int(11) NOT NULL,
  `fecha_vacunacion` datetime NOT NULL,
  `id_personal_medico` int(11) NOT NULL,
  `lugar_vacunacion` varchar(200) NOT NULL,
  `observaciones` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registros_vacunacion`
--

INSERT INTO `registros_vacunacion` (`id`, `id_paciente`, `id_vacuna`, `fecha_vacunacion`, `id_personal_medico`, `lugar_vacunacion`, `observaciones`, `created_at`) VALUES
(1, 1, 1, '2023-05-10 09:30:00', 1, 'Centro de Salud Norte', 'Primera dosis', '2026-04-23 14:35:30'),
(2, 1, 1, '2023-05-31 10:15:00', 1, 'Centro de Salud Norte', 'Segunda dosis', '2026-04-23 14:35:30'),
(3, 2, 2, '2023-06-05 11:00:00', 1, 'Hospital General', 'Dosis única', '2026-04-23 14:35:30'),
(4, 3, 3, '2023-06-10 14:30:00', 1, 'Centro de Vacunación', 'Vacunación anual', '2026-04-23 14:35:30'),
(5, 4, 4, '2023-06-15 16:00:00', 1, 'Clínica de Especialidades', 'Primera dosis de tres', '2026-04-23 14:35:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombres` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `documento` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `rol` enum('MEDICO','ENFERMERO') NOT NULL,
  `especialidad` varchar(100) DEFAULT NULL,
  `institucion` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombres`, `apellidos`, `documento`, `email`, `username`, `password`, `rol`, `especialidad`, `institucion`, `created_at`) VALUES
(1, 'Admin', 'Sistema', '1000000000', 'admin@example.com', 'admin', 'admin123', 'MEDICO', 'Administración', 'Hospital General', '2026-04-23 14:35:29');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacunas`
--

CREATE TABLE `vacunas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `lote` varchar(50) NOT NULL,
  `laboratorio` varchar(100) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vacunas`
--

INSERT INTO `vacunas` (`id`, `nombre`, `lote`, `laboratorio`, `fecha_vencimiento`, `created_at`) VALUES
(1, 'COVID-19 Pfizer', 'PF001', 'Pfizer', '2026-12-31', '2026-04-23 14:35:29'),
(2, 'COVID-19 Moderna', 'MD001', 'Moderna', '2026-10-15', '2026-04-23 14:35:29'),
(3, 'Influenza', 'IN001', 'Sanofi', '2025-08-22', '2026-04-23 14:35:29'),
(4, 'Hepatitis B', 'HB001', 'GSK', '2026-05-10', '2026-04-23 14:35:29'),
(5, 'Triple Viral (SRP)', 'TV001', 'Serum Institute', '2025-11-30', '2026-04-23 14:35:29');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `log_cambios`
--
ALTER TABLE `log_cambios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `documento` (`documento`);

--
-- Indices de la tabla `registros_vacunacion`
--
ALTER TABLE `registros_vacunacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_paciente` (`id_paciente`),
  ADD KEY `id_vacuna` (`id_vacuna`),
  ADD KEY `id_personal_medico` (`id_personal_medico`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `documento` (`documento`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indices de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uk_lote_nombre` (`lote`,`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `log_cambios`
--
ALTER TABLE `log_cambios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `registros_vacunacion`
--
ALTER TABLE `registros_vacunacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `vacunas`
--
ALTER TABLE `vacunas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `log_cambios`
--
ALTER TABLE `log_cambios`
  ADD CONSTRAINT `log_cambios_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `registros_vacunacion`
--
ALTER TABLE `registros_vacunacion`
  ADD CONSTRAINT `registros_vacunacion_ibfk_1` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id`),
  ADD CONSTRAINT `registros_vacunacion_ibfk_2` FOREIGN KEY (`id_vacuna`) REFERENCES `vacunas` (`id`),
  ADD CONSTRAINT `registros_vacunacion_ibfk_3` FOREIGN KEY (`id_personal_medico`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
