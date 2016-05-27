-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27-Maio-2016 às 13:53
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `snackbar`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `balconista`
--

CREATE TABLE IF NOT EXISTS `balconista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `data_nascimento` date NOT NULL,
  `morada` varchar(255) DEFAULT NULL,
  `nuit` int(11) NOT NULL,
  `username` varchar(16) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipousuario_id` int(11) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipouruario_id` (`tipousuario_id`),
  KEY `tipouruario_id_2` (`tipousuario_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `balconista`
--

INSERT INTO `balconista` (`id`, `nome`, `data_nascimento`, `morada`, `nuit`, `username`, `password`, `tipousuario_id`, `activo`) VALUES
(1, 'Celso', '2016-05-05', 'MAgoanine', 554646, 'user1', 'password1', 1, 1),
(2, 'Osvaldo', '2016-05-05', 'Polana cimento', 546468, 'user2', 'password2', 1, 1),
(8, 'Frenque', '2016-05-11', 'asdasd', 5464, 'user3', 'password3', 1, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `tipoitem_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tipoitem_id` (`tipoitem_id`),
  KEY `tipoitem_id_2` (`tipoitem_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `item`
--

INSERT INTO `item` (`id`, `nome`, `preco`, `tipoitem_id`) VALUES
(1, 'Refresco', 70, 2),
(2, 'Amarula', 230, 1),
(3, 'Heineken', 50, 1),
(4, 'Dose de Frango', 140, 3),
(5, 'Xima', 50, 3),
(6, 'Salada de Tomate', 60, 3),
(7, 'Sumo Ceres', 60, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_pedido`
--

CREATE TABLE IF NOT EXISTS `item_pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `qtd` int(255) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`,`pedido_id`),
  KEY `pedido_id` (`pedido_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Extraindo dados da tabela `item_pedido`
--

INSERT INTO `item_pedido` (`id`, `item_id`, `qtd`, `pedido_id`, `data`) VALUES
(3, 1, 5, 3, '2016-05-24 20:54:59'),
(4, 1, 4, 4, '2016-05-25 20:57:02'),
(5, 1, 5, 5, '2016-05-24 21:09:37'),
(6, 1, 5, 6, '2016-05-26 11:34:10'),
(7, 1, 4, 6, '2016-05-26 11:34:16'),
(8, 1, 66, 6, '2016-05-26 11:34:20'),
(11, 1, 8, 8, '2016-05-26 19:07:07'),
(12, 1, 7, 9, '2016-05-26 19:08:10'),
(13, 1, 7, 10, '2016-05-26 19:11:03'),
(14, 4, 3, 11, '2016-05-26 19:21:07'),
(15, 1, 4, 14, '2016-05-27 10:36:11'),
(16, 1, 4, 16, '2016-05-27 10:37:14');

-- --------------------------------------------------------

--
-- Estrutura da tabela `logs`
--

CREATE TABLE IF NOT EXISTS `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balconista_id` int(11) NOT NULL,
  `accao` varchar(255) NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `balconista_id` (`balconista_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mesa`
--

CREATE TABLE IF NOT EXISTS `mesa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `mesalivre` tinyint(1) NOT NULL,
  `activa` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `mesa`
--

INSERT INTO `mesa` (`id`, `numero`, `mesalivre`, `activa`) VALUES
(1, 1, 0, 1),
(2, 2, 1, 1),
(3, 3, 1, 1),
(4, 4, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balconista_id` int(11) NOT NULL,
  `mesa_id` int(11) NOT NULL,
  `pago` tinyint(1) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `balconista` (`balconista_id`,`mesa_id`),
  KEY `balconista_id` (`balconista_id`),
  KEY `mesa_id` (`mesa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Extraindo dados da tabela `pedido`
--

INSERT INTO `pedido` (`id`, `balconista_id`, `mesa_id`, `pago`, `data`) VALUES
(3, 1, 1, 1, '2016-05-25 20:57:16'),
(4, 1, 1, 1, '2016-05-25 20:57:16'),
(5, 1, 1, 1, '2016-05-24 21:09:53'),
(6, 1, 1, 1, '2016-05-26 11:34:41'),
(8, 1, 1, 1, '2016-05-26 19:08:37'),
(9, 1, 1, 1, '2016-05-26 19:08:37'),
(10, 1, 1, 1, '2016-05-26 19:11:40'),
(11, 1, 1, 1, '2016-05-26 19:21:20'),
(12, 1, 1, 0, '2016-05-27 10:35:44'),
(13, 1, 1, 0, '2016-05-27 10:35:55'),
(14, 1, 1, 0, '2016-05-27 10:36:13'),
(15, 1, 1, 0, '2016-05-27 10:37:08'),
(16, 1, 1, 0, '2016-05-27 10:37:24');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoitem`
--

CREATE TABLE IF NOT EXISTS `tipoitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tipoitem`
--

INSERT INTO `tipoitem` (`id`, `nome`) VALUES
(1, 'Bebida Alcoolica'),
(2, 'Bebida Nao Alcoolica'),
(3, 'Comida');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipousuario`
--

CREATE TABLE IF NOT EXISTS `tipousuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `designacao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `designacao`) VALUES
(1, 'Proprietario'),
(2, 'Gerente');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `balconista`
--
ALTER TABLE `balconista`
  ADD CONSTRAINT `balconista_ibfk_1` FOREIGN KEY (`tipousuario_id`) REFERENCES `tipousuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`tipoitem_id`) REFERENCES `tipoitem` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `item_pedido_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `item_pedido_ibfk_2` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`balconista_id`) REFERENCES `balconista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`mesa_id`) REFERENCES `mesa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`balconista_id`) REFERENCES `balconista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
