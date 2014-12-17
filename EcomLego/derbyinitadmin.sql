connect 'jdbc:derby://localhost:1527/sun-appserv-samples;user=APP;password=APP;create=true';
INSERT INTO USERACCOUNT(IDU, USER_MAIL, BILLINGADDRESS, SHIPPINGADDRESS, MDPU, RIB, CELLPHONE, FIXPHONE )
values (0, 'admin@byl.com', 'billingAddress', 'shipping address', '32c074519f1b26f020a134a253ce3c6222674ec9', '0123456789', '0123456789', '0123456789');
INSERT INTO ADMINISTRATOR(IDA, NAMEA, PRENOMA)
values (0, 'ADMIN', 'ADMIN');
INSERT INTO Catalogue(DATECAT,IDCAT)
values ('unedata','uneref');
INSERT INTO Picture(IDP,NOMP,USERP)
values (0,'nomp','userp');
INSERT INTO CreatePiece(IDCP,NAMECP,PRICECP,COLORCP,THEMECP)
values (0,'createpiece',5.0,'acolor','atheme');
INSERT INTO OriginalPiece(IDPO,NAMEPO,PRICEPO,COLOROP,THEMEOP)
values (0,'OriginalPiece',6.0,'red','lego');
disconnect;
exit;
