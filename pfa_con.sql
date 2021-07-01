

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



create Database `pfa_con`;
use `pfa_con`;
--

-- --------------------------------------------------------

--
-- Table structure for table `covoiturage`
--

CREATE TABLE `covoiturage` (
  `id` bigint(20) NOT NULL,
  `nbr_places` int(11) NOT NULL,
  `trajet_id` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trajet`
--

CREATE TABLE `trajet` (
  `id` bigint(20) NOT NULL,
  `type_trajet` varchar(20) NOT NULL,
  `villarrive` varchar(64) NOT NULL,
  `cout` int(11) NOT NULL,
  `date_arrivee` datetime DEFAULT NULL,
  `datedepart` datetime DEFAULT NULL,
  `nbr_places` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `villedepart` varchar(45) NOT NULL,
  `id_user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trajet`
--

INSERT INTO `trajet` (`id`, `type_trajet`, `villarrive`, `cout`, `date_arrivee`, `datedepart`, `nbr_places`, `status`, `villedepart`, `id_user`) VALUES
(4, 'Ponctuel', 'Bni Drar', 11, '2021-06-30 11:55:42', '2021-06-30 11:55:39', 1, 0, 'Oujda', 1),
(6, 'Ponctuel', 'Agadir', 10, '2021-06-24 03:00:00', '2021-06-24 11:35:50', 4, 0, 'Oujda', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `roole` varchar(255) DEFAULT NULL,
  `state` bit(1) NOT NULL,
  `tel` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `gender`, `nom`, `pass`, `prenom`, `roole`, `state`, `tel`) VALUES
(1, 'youness@gmail.com', 'femme', 'bahi', '$2a$10$GarHPGSEoh.pG12ADdeywOkwLpdl6G/NM.5wvuKNRoiRz3qI91dAW', 'YOUNESS', NULL, b'0', '0606223344'),
(2, 'imades@gmail.com', 'femme', 'AAAA', '$2a$10$Jp2b2SHDy9xWi2/PAQqu5eGuDs/0XSim8JEXWqAvCC8qMmIX7KfmS', 'IMADES', NULL, b'0', '0606443322'),
(3, 'im@gmail.com', 'homme', 'IMAD', '$2a$10$SQI6TBvU88AJW74bsqdR.ONqLr4iNk88r.J53uqVKRrF1aUXiKKfC', 'IMAD', 'ROLE_USER', b'1', '0606331122'),
(4, 'y@y.gmail.com', 'homme', 'Bahi', '$2a$10$XivM2sOgksLOj7bdZNswDeCUkRP/s9guqUTz846KOeG7eHVg/WSsa', 'Youness', 'ROLE_USER', b'1', '0123456789'),
(5, 'y@you.gmail.com', 'homme', 'Bahi', '$2a$10$hFW5udnN3hkm1/0TZaSWKOEI1Rp0jbPlO/oPrJK4XAPuAvHEHb7FG', 'benyouness', 'ROLE_USER', b'1', '0123456789'),
(6, 'y@younesss.gmail.com', 'homme', 'bahi', '$2a$10$G3Ac7uO/8mZ1CSDFLk.8YuXbvFjDypUYj6CtVnWlkRhC4X2nRkytC', 'benyouness', 'ROLE_ADMIN', b'1', '0123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `covoiturage`
--
ALTER TABLE `covoiturage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlo4sqimwhoknekbqay74ee5c6` (`trajet_id`),
  ADD KEY `FKarqfccqkwp0hn1eyqtkerp0tj` (`id_user`);

--
-- Indexes for table `trajet`
--
ALTER TABLE `trajet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgn9mnmylurs6y3wck84ovp4pm` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `covoiturage`
--
ALTER TABLE `covoiturage`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trajet`
--
ALTER TABLE `trajet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `covoiturage`
--
ALTER TABLE `covoiturage`
  ADD CONSTRAINT `FKarqfccqkwp0hn1eyqtkerp0tj` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKlo4sqimwhoknekbqay74ee5c6` FOREIGN KEY (`trajet_id`) REFERENCES `trajet` (`id`);

--
-- Constraints for table `trajet`
--
ALTER TABLE `trajet`
  ADD CONSTRAINT `FKgn9mnmylurs6y3wck84ovp4pm` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
