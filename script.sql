--
-- PostgreSQL database dump
--

-- Dumped from database version 10.7
-- Dumped by pg_dump version 10.7

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
--SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: contacts; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA contacts;


ALTER SCHEMA contacts OWNER TO postgres;

--
-- Name: gender; Type: TYPE; Schema: contacts; Owner: postgres
--

CREATE TYPE contacts.gender AS ENUM (
    'male',
    'female',
    ''
);


ALTER TYPE contacts.gender OWNER TO postgres;

--
-- Name: marital_status; Type: TYPE; Schema: contacts; Owner: postgres
--

CREATE TYPE contacts.marital_status AS ENUM (
    'single',
    'in a relationship',
    'married',
    ''
);


ALTER TYPE contacts.marital_status OWNER TO postgres;

--
-- Name: phone_type; Type: TYPE; Schema: contacts; Owner: postgres
--

CREATE TYPE contacts.phone_type AS ENUM (
    'home',
    'mobile',
    ''
);


ALTER TYPE contacts.phone_type OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: attachments; Type: TABLE; Schema: contacts; Owner: postgres
--

CREATE TABLE contacts.attachments (
    contact_id integer NOT NULL,
    path character(50),
    date date,
    note character(30),
    id integer NOT NULL,
    attach_name character(50)
);


ALTER TABLE contacts.attachments OWNER TO postgres;

--
-- Name: attachments_id_seq; Type: SEQUENCE; Schema: contacts; Owner: postgres
--

CREATE SEQUENCE contacts.attachments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts.attachments_id_seq OWNER TO postgres;

--
-- Name: attachments_id_seq; Type: SEQUENCE OWNED BY; Schema: contacts; Owner: postgres
--

ALTER SEQUENCE contacts.attachments_id_seq OWNED BY contacts.attachments.id;


--
-- Name: contact; Type: TABLE; Schema: contacts; Owner: postgres
--

CREATE TABLE contacts.contact (
    id smallint NOT NULL,
    first_name character(20) NOT NULL,
    last_name character(20) NOT NULL,
    middle_name character(20),
    birth_date date,
    citizenship character(30),
    web_site character(50),
    email character(30),
    company character(50),
    country character(20),
    city character(20),
    street character(30),
    house character(10),
    flat character(10),
    zip_code integer,
    marital_status contacts.marital_status,
    gender contacts.gender,
    avatar character(50)
);


ALTER TABLE contacts.contact OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE; Schema: contacts; Owner: postgres
--

CREATE SEQUENCE contacts.contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts.contact_id_seq OWNER TO postgres;

--
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: contacts; Owner: postgres
--

ALTER SEQUENCE contacts.contact_id_seq OWNED BY contacts.contact.id;


--
-- Name: number; Type: TABLE; Schema: contacts; Owner: postgres
--

CREATE TABLE contacts.number (
    id smallint NOT NULL,
    contact_id smallint NOT NULL,
    phone integer NOT NULL,
    country_code smallint,
    operator_code smallint,
    note character(30),
    type contacts.phone_type
);


ALTER TABLE contacts.number OWNER TO postgres;

--
-- Name: number_id_seq; Type: SEQUENCE; Schema: contacts; Owner: postgres
--

CREATE SEQUENCE contacts.number_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contacts.number_id_seq OWNER TO postgres;

--
-- Name: number_id_seq; Type: SEQUENCE OWNED BY; Schema: contacts; Owner: postgres
--

ALTER SEQUENCE contacts.number_id_seq OWNED BY contacts.number.id;


--
-- Name: attachments id; Type: DEFAULT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.attachments ALTER COLUMN id SET DEFAULT nextval('contacts.attachments_id_seq'::regclass);


--
-- Name: contact id; Type: DEFAULT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.contact ALTER COLUMN id SET DEFAULT nextval('contacts.contact_id_seq'::regclass);


--
-- Name: number id; Type: DEFAULT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.number ALTER COLUMN id SET DEFAULT nextval('contacts.number_id_seq'::regclass);


--
-- Data for Name: attachments; Type: TABLE DATA; Schema: contacts; Owner: postgres
--

COPY contacts.attachments (contact_id, path, date, note, id, attach_name) FROM stdin;
1	13_black_and_white.jpg                            	2018-09-02	                              	26	13_black_and_white.jpg                            
\.


--
-- Data for Name: contact; Type: TABLE DATA; Schema: contacts; Owner: postgres
--

COPY contacts.contact (id, first_name, last_name, middle_name, birth_date, citizenship, web_site, email, company, country, city, street, house, flat, zip_code, marital_status, gender, avatar) FROM stdin;
115	John                	Wick                	                    	1970-07-22	                              	                                                  	contactslist.okvol@gmail.com  	                                                  	USA                 	New York            	                              	          	          	\N		male	\N
7	Steven              	Rogers              	Joseph              	1918-07-04	                              	                                                  	                              	                                                  	USA                 	New York            	Brooklyn                      	          	          	\N	married	male	\N
1	Джессика            	Джонс               	Васильевна          	\N	                              	                                                  	kate.okvol@gmail.com          	Под псевдонимом                                   	США                 	Нью-Йорк            	                              	          	          	\N	in a relationship	female	1.jpg                                             
8	Harry               	Potter              	James               	1980-07-29	                              	                                                  	                              	                                                  	Great Britain       	London              	                              	          	          	\N	in a relationship	male	potter.jpg                                        
20	Hermione            	Granger             	                    	1980-07-26	                              	                                                  	golovkokatrin2000@gmail.com   	Министерство магии                                	Великобритания      	Лондон              	                              	          	          	\N	married	female	206487375.jpg                                     
116	Harry               	Potter              	James               	1980-07-30	                              	                                                  	                              	                                                  	Great Britain       	London              	                              	          	          	\N	in a relationship	male	\N
117	Рон                 	Уизли               	                    	\N	                              	                                                  	                              	                                                  	                    	                    	                              	          	          	\N	married	male	OVhsxIDjGA0.jpg                                   
118	John                	Wick                	                    	1970-07-22	                              	                                                  	contactslist.okvol@gmail.com  	                                                  	USA                 	New York            	                              	          	          	\N		male	\N
\.


--
-- Data for Name: number; Type: TABLE DATA; Schema: contacts; Owner: postgres
--

COPY contacts.number (id, contact_id, phone, country_code, operator_code, note, type) FROM stdin;
8	1	98546	\N	\N	\N	home
9	1	5796182	375	29	agency contact number         	mobile
22	8	453735	854	56	вместо совиной почты          	mobile
23	20	78645	741	44	лолрпсамит                    	mobile
24	20	552435	852	85	рпп  ла                       	mobile
25	20	996364	963	96	нльплмлпм                     	mobile
49	115	542174	375	29	для связи с белорусами        	mobile
48	115	86758	\N	\N	дом сгорел в первой части     	home
50	115	85274	\N	\N	новый                         	home
51	1	4546874	1	687	Люк Кейдж                     	mobile
20	1	235547	315	63	странный номер                	mobile
\.



ALTER TABLE ONLY contacts.attachments
    ADD CONSTRAINT attachments_pk PRIMARY KEY (id);


--
-- Name: contact contact_pkey; Type: CONSTRAINT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- Name: number number_id; Type: CONSTRAINT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.number
    ADD CONSTRAINT number_id PRIMARY KEY (id);


--
-- Name: attachments_id_uindex; Type: INDEX; Schema: contacts; Owner: postgres
--

CREATE UNIQUE INDEX attachments_id_uindex ON contacts.attachments USING btree (id);


--
-- Name: number contact_id; Type: FK CONSTRAINT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.number
    ADD CONSTRAINT contact_id FOREIGN KEY (contact_id) REFERENCES contacts.contact(id);


--
-- Name: attachments contact_id; Type: FK CONSTRAINT; Schema: contacts; Owner: postgres
--

ALTER TABLE ONLY contacts.attachments
    ADD CONSTRAINT contact_id FOREIGN KEY (contact_id) REFERENCES contacts.contact(id);


--
-- PostgreSQL database dump complete
--

