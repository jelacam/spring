insert into organization values ('f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9','C',0)
insert into organization values ('4be64d42-31ed-46ba-8030-8b17f9222928','A',1)
insert into organization values ('64cb5ca0-90e5-419b-9836-e2dff0bbd9a9','B',0)


insert into admin values ('2c8b9fb2-ed82-449e-b95a-28f19d45cfe0', 'Sloba', 'Stankovic',	'stankovic', 'q0dCVyk+pXjiWRuGe0RIWBny+qkGx94X5PeGYzmGl3E=', 'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')
insert into admin values ('816c22ff-eb4c-4e6d-a283-cfbe3edc481d',	'Zora',	'Zoric',	'zoric',	'KIGbPnD5SwKSghdCcsoXSpfxeWA9+0D4ibNq5NSBOQY=',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9')
insert into admin values ('f775dca6-8de1-4aad-af9c-8e1cb49a3b70',	'Mika',	'Mikic',	'mikic',	'27l8ncgTRAIpx7meVbQYNaWxC6/dfkk/40L4jr1i7EU=',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9')
insert into admin values ('9f90dda1-e12b-4135-a774-1631423973c2',	'Pera',	'Peric',	'peric',	'h00Po+4QZVREo6W7UeT8rMXKKVRuOS4UPmU/JifBMSQ=',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9')
insert into admin values ('c021ac97-04e4-4f21-aa6a-66bb37ea484d',	'Jovana',	'Jovanovic',	'jovanovic',	'jkt6BA2TBmRiihr2yPqjw9rX6vT+Q6l1ytYBOPsYX2E=',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')
insert into admin values ('e6ab1273-cb54-4926-9178-85bd164b77b7',	'Nemanja',	'Nemanjic',	'nemanjic',	'BvuXORN3lhj9PPku4YS4TPTQrprZLJR4xnYJCvAwLEs=',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')


insert into role values ('168dee46-eb99-4eb5-8dda-94c03994a405', 'ADMIN_B_R','64cb5ca0-90e5-419b-9836-e2dff0bbd9a9')
insert into role values ('ccab48c2-84e8-49a5-907a-69154384d636', 'ADMIN_B_CRUD','64cb5ca0-90e5-419b-9836-e2dff0bbd9a9')
insert into role values ('d97acadb-efe4-484f-9397-c23c4a006606', 'ADMIN_C_CRUD','f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')
insert into role values ('db5502e0-059a-4be1-8646-dd8fffbd9dd1', 'ADMIN_C_R','f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')
insert into role values ('92ff9ec0-e630-4b6f-a94e-6a40580f7725', 'ADMIN_C_CRU','f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9')


insert into permission values ('52c3aa5e-0c65-4bee-aefd-8637688523c6',	3,	0, '92ff9ec0-e630-4b6f-a94e-6a40580f7725')
insert into permission values ('e8a3f4bf-5788-4696-adcd-0297e90f07f3',	3,	1, '92ff9ec0-e630-4b6f-a94e-6a40580f7725')
insert into permission values ('1b6da2e8-fb5b-4e1a-ac4f-e7530eb92faf',	3,	1, 'db5502e0-059a-4be1-8646-dd8fffbd9dd1')
insert into permission values ('0aa8cbf1-3431-4882-bb67-2d0d0a491a99',	3,	3, 'd97acadb-efe4-484f-9397-c23c4a006606')
insert into permission values ('7edce105-59ae-4a0a-be39-8a8227aa934b',	3,	2, 'd97acadb-efe4-484f-9397-c23c4a006606')
insert into permission values ('bd178a59-7a38-4518-b16b-e7d8ece6af2e',	3,	1, 'd97acadb-efe4-484f-9397-c23c4a006606')
insert into permission values ('992ce600-b827-4809-a3c3-2622d3e0d025',	3,	0, 'd97acadb-efe4-484f-9397-c23c4a006606')
insert into permission values ('ce6272a4-8c64-4f67-bbf5-e75e4511efad',	3,	1, '168dee46-eb99-4eb5-8dda-94c03994a405')
insert into permission values ('4fd67ba9-0620-4ed6-9075-1769b11cb890',	3,	3, 'ccab48c2-84e8-49a5-907a-69154384d636')
insert into permission values ('3ff490ec-ca1d-46ad-a7aa-6e25a106e970',	3,	2, 'ccab48c2-84e8-49a5-907a-69154384d636')
insert into permission values ('013941e6-5808-4675-a4b1-68cd66dfb9cb',	3,	1, 'ccab48c2-84e8-49a5-907a-69154384d636')
insert into permission values ('e4dbda05-a078-4339-ad47-d6abcad19acf',	3,	0, 'ccab48c2-84e8-49a5-907a-69154384d636')
insert into permission values ('d4bfd815-20f2-44c3-bf43-a3c502480216',	3,	2, '92ff9ec0-e630-4b6f-a94e-6a40580f7725')
insert into permission values ('6b5fbdcf-dc15-4afe-9a44-819a7d9fb9bb',	0,	1, '168dee46-eb99-4eb5-8dda-94c03994a405')
insert into permission values ('8a5d86f2-4310-4f6a-a887-2aee7f37821f',	0,	2, '168dee46-eb99-4eb5-8dda-94c03994a405')



insert into adminrole values ('8ebf63c5-8767-4d52-b6e8-cf8eee878050','816c22ff-eb4c-4e6d-a283-cfbe3edc481d','168dee46-eb99-4eb5-8dda-94c03994a405')
insert into adminrole values ('91bbbc9c-6fdb-4839-818b-2ef1baa0319f','9f90dda1-e12b-4135-a774-1631423973c2','ccab48c2-84e8-49a5-907a-69154384d636')
insert into adminrole values ('c20602ea-04bc-466d-84b9-ab2902febdca','f775dca6-8de1-4aad-af9c-8e1cb49a3b70','168dee46-eb99-4eb5-8dda-94c03994a405')
insert into adminrole values ('7bddd21c-b674-427b-b249-6d42b6a93860','2c8b9fb2-ed82-449e-b95a-28f19d45cfe0','d97acadb-efe4-484f-9397-c23c4a006606')
insert into adminrole values ('0c2382b4-7579-4a21-b473-f0abc23731b8','c021ac97-04e4-4f21-aa6a-66bb37ea484d','92ff9ec0-e630-4b6f-a94e-6a40580f7725')
insert into adminrole values ('cc745599-c93e-438d-a9dd-e8f8a5a58261','e6ab1273-cb54-4926-9178-85bd164b77b7','db5502e0-059a-4be1-8646-dd8fffbd9dd1')



insert into product values ('bc0cde52-9c5a-4c0d-8083-9ba2621956a6',	'Kuciste',	'Opis kucista',	200, '64cb5ca0-90e5-419b-9836-e2dff0bbd9a9', 20)
insert into product values ('ab11304d-5ed7-4a5c-bc96-830c6172f8e0',	'Osigurac',	'Opis osiguraca',20, '64cb5ca0-90e5-419b-9836-e2dff0bbd9a9', 120)
insert into product values ('d0cdd8f0-73f1-48f9-916b-4fa27fb77a87',	'Energetski kabel',	'Opis energetskog kabela',	100, '64cb5ca0-90e5-419b-9836-e2dff0bbd9a9', 8)
insert into product values ('5950dea8-eb29-485f-850c-cb52eff12f30',	'Elektromotor',	'Novi opis elektromotora',	550, '64cb5ca0-90e5-419b-9836-e2dff0bbd9a9', 5)
insert into product values ('7106dc5e-b6e6-4507-8504-2d866e140242',	'Bojler',	'Novi opis bojlera',	550, 'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9', 5)
insert into product values ('ac9655c1-4866-4a05-8d99-c5f687274491',	'Sporet',	'Opis sporeta',	1200, 'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9', 2)
insert into product values ('d606a632-8178-45b9-bc93-1a4b05f2d6c5',	'Sudo masina',	'Opis sudo masine',	800, 'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9', 7)



insert into productsharingstatement values ('ad2b934c-3386-453e-ac59-603fdcf7e8df',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9', 10,	null,	1,	2,	1)
insert into productsharingstatement values ('0d4596a9-522e-43b2-a9e6-919975ee613f',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9', 10,	null,	1,	1,	1)
insert into productsharingstatement values ('15a26dd0-b7fd-408d-9abf-977f1ed66fc8',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9',	10,	null,	3,	1,	1)
insert into productsharingstatement values ('a6aa6117-d1d9-491b-8215-c6ca4bd57985',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9',	10,	null,	1,	3,	1)
insert into productsharingstatement values ('17ffe463-d2fd-4dcd-8217-1763eb24875d',	'f17ab63a-7ef6-4fe1-80c4-78cd7d3aa0e9',	'64cb5ca0-90e5-419b-9836-e2dff0bbd9a9',	null,	null,	null,	1,	1)



