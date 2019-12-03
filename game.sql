-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2019 at 07:52 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `game`
--

-- --------------------------------------------------------

--
-- Table structure for table `armour`
--

CREATE TABLE `armour` (
  `aid` int(11) NOT NULL,
  `aname` varchar(50) NOT NULL,
  `clid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `armour`
--

INSERT INTO `armour` (`aid`, `aname`, `clid`) VALUES
(1, 'transcendance', 1),
(2, 'iron will', 1),
(3, 'armamentarium', 1),
(4, 'hyperion', 2),
(5, 'hallowfire', 2),
(6, 'actium', 2),
(7, 'shadow stepper', 3),
(8, 'dreambane', 3),
(9, 'ophidia spathe', 3),
(10, 'chromatic fire', 4),
(11, 'phoenix protocol', 4),
(12, 'Sanguine Alchemy', 4);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `cid` int(11) NOT NULL,
  `cname` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`cid`, `cname`) VALUES
(3, 'assassin'),
(2, 'gunslinger'),
(4, 'mage'),
(1, 'titan');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `playerid` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `level` int(11) NOT NULL DEFAULT 1,
  `classid` int(11) DEFAULT NULL,
  `weaponid` int(11) DEFAULT NULL,
  `armourid` int(11) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `stats`
--

CREATE TABLE `stats` (
  `pid` int(11) NOT NULL,
  `maxhp` int(11) NOT NULL DEFAULT 10,
  `maxmp` int(11) NOT NULL DEFAULT 10,
  `phys_attack` int(11) NOT NULL DEFAULT 10,
  `magic_attack` int(11) NOT NULL DEFAULT 10,
  `defence` int(11) NOT NULL DEFAULT 10,
  `recovery` int(11) NOT NULL DEFAULT 10,
  `mobility` int(11) NOT NULL DEFAULT 10
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `weapons`
--

CREATE TABLE `weapons` (
  `wid` int(11) NOT NULL,
  `wname` varchar(50) NOT NULL,
  `clid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `weapons`
--

INSERT INTO `weapons` (`wid`, `wname`, `clid`) VALUES
(1, 'excalibur sword', 1),
(2, 'shadowsteel longsword', 1),
(3, 'willbreaker battle axe', 1),
(4, 'duke mk.44 revolver', 2),
(5, 'tarrabah smg', 2),
(6, 'blasphemer shotgun', 2),
(7, 'karambit dagger', 3),
(8, 'widowmaker blade', 3),
(9, 'shadowstrike hidden blade', 3),
(10, 'wand of healing', 4),
(11, 'staff of despair', 4),
(12, 'spellcasting', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `armour`
--
ALTER TABLE `armour`
  ADD PRIMARY KEY (`aid`),
  ADD UNIQUE KEY `aname` (`aname`),
  ADD UNIQUE KEY `aname_2` (`aname`),
  ADD KEY `aclassfk` (`clid`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`cid`),
  ADD UNIQUE KEY `cname` (`cname`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`playerid`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `armourfk` (`armourid`),
  ADD KEY `weaponfk` (`weaponid`),
  ADD KEY `classfk` (`classid`);

--
-- Indexes for table `stats`
--
ALTER TABLE `stats`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `weapons`
--
ALTER TABLE `weapons`
  ADD PRIMARY KEY (`wid`),
  ADD UNIQUE KEY `wname` (`wname`),
  ADD UNIQUE KEY `wname_2` (`wname`),
  ADD KEY `wclassfk` (`clid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `player`
--
ALTER TABLE `player`
  MODIFY `playerid` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `armour`
--
ALTER TABLE `armour`
  ADD CONSTRAINT `aclassfk` FOREIGN KEY (`clid`) REFERENCES `class` (`cid`) ON UPDATE CASCADE;

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `armourfk` FOREIGN KEY (`armourid`) REFERENCES `armour` (`aid`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `classfk` FOREIGN KEY (`classid`) REFERENCES `class` (`cid`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `weaponfk` FOREIGN KEY (`weaponid`) REFERENCES `weapons` (`wid`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `stats`
--
ALTER TABLE `stats`
  ADD CONSTRAINT `statfk` FOREIGN KEY (`pid`) REFERENCES `player` (`playerid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stats_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `player` (`playerid`),
  ADD CONSTRAINT `stats_ibfk_2` FOREIGN KEY (`pid`) REFERENCES `player` (`playerid`) ON UPDATE CASCADE;

--
-- Constraints for table `weapons`
--
ALTER TABLE `weapons`
  ADD CONSTRAINT `wclassfk` FOREIGN KEY (`clid`) REFERENCES `class` (`cid`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
