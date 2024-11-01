SELECT
    rp.familyName AS relative_family_name,
    rp.givenName AS relative_given_name,
    rp.middleName AS relative_middle_name,
    rp.birthDate AS relative_birth_date,
    pd.contactRelationship AS relationship
FROM
    HPPersonDependant pd
JOIN
    HPPersonGeneric gp ON pd.HPPersonGenericSysId = gp.sysId
JOIN
    HPPersonGeneric rp ON pd.HPRelatedPersonSysId = rp.sysId
WHERE
    gp.personId = 'test';
