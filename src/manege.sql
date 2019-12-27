USE [master]
GO
/****** Object:  Database [Web9]    Script Date: 12/26/2019 13:27:22 ******/
CREATE DATABASE [Web9] ON  PRIMARY 
( NAME = N'Web9', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Web9.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Web9_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\Web9_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Web9] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Web9].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Web9] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [Web9] SET ANSI_NULLS OFF
GO
ALTER DATABASE [Web9] SET ANSI_PADDING OFF
GO
ALTER DATABASE [Web9] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [Web9] SET ARITHABORT OFF
GO
ALTER DATABASE [Web9] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [Web9] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [Web9] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [Web9] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [Web9] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [Web9] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [Web9] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [Web9] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [Web9] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [Web9] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [Web9] SET  DISABLE_BROKER
GO
ALTER DATABASE [Web9] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [Web9] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [Web9] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [Web9] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [Web9] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [Web9] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [Web9] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [Web9] SET  READ_WRITE
GO
ALTER DATABASE [Web9] SET RECOVERY SIMPLE
GO
ALTER DATABASE [Web9] SET  MULTI_USER
GO
ALTER DATABASE [Web9] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [Web9] SET DB_CHAINING OFF
GO
USE [Web9]
GO
/****** Object:  User [hwt]    Script Date: 12/26/2019 13:27:22 ******/
CREATE USER [hwt] FOR LOGIN [hwt] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[system]    Script Date: 12/26/2019 13:27:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[system](
	[systemname] [char](20) NULL,
	[username] [char](20) NULL,
	[password] [char](20) NULL,
	[phone] [char](15) NULL,
	[email] [char](15) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[maneger]    Script Date: 12/26/2019 13:27:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[maneger](
	[manegername] [char](100) NULL,
	[username] [char](100) NULL,
	[phone] [char](20) NULL,
	[email] [char](50) NULL,
	[departmentid] [int] NULL,
	[password] [char](100) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[information]    Script Date: 12/26/2019 13:27:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[information](
	[meetingid] [char](32) NULL,
	[username] [char](20) NULL,
	[name] [char](10) NULL,
	[sex] [char](10) NULL,
	[id] [char](20) NULL,
	[workplace] [char](20) NULL,
	[tele] [char](20) NULL,
	[time] [char](20) NULL,
	[room] [char](10) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[employee]    Script Date: 12/26/2019 13:27:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[employee](
	[employeename] [char](20) NULL,
	[username] [char](20) NULL,
	[phone] [char](20) NULL,
	[email] [char](20) NULL,
	[departmentid] [int] NULL,
	[password] [char](20) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[addmeeting]    Script Date: 12/26/2019 13:27:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[addmeeting](
	[date] [char](10) NULL,
	[meetingposition] [char](8) NULL,
	[people] [char](20) NULL,
	[hotel] [char](20) NULL,
	[meetingid] [char](32) NULL,
	[name] [char](2) NULL,
	[sex] [char](2) NULL,
	[id] [char](2) NULL,
	[workplace] [char](2) NULL,
	[tele] [char](2) NULL,
	[time] [char](2) NULL,
	[room] [char](2) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
