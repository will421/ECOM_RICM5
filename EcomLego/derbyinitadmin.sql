connect 'jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true';
INSERT INTO USERACCOUNT(IDU, USER_MAIL, BILLINGADDRESS, SHIPPINGADDRESS, MDPU, RIB, CELLPHONE, FIXPHONE )
values (0, 'admin@byl.com', 'billingAddress', 'shipping address', '32c074519f1b26f020a134a253ce3c6222674ec9', '0123456789', '0123456789', '0123456789');
INSERT INTO ADMINISTRATOR(IDA, NAMEA, PRENOMA)
values (0, 'ADMIN', 'ADMIN');
disconnect;
exit;
