package ee.sk.digidoc4j.utils;

import ee.sk.digidoc4j.Signer;
import ee.sk.digidoc4j.X509Cert;
import eu.europa.ec.markt.dss.DigestAlgorithm;
import eu.europa.ec.markt.dss.signature.token.AbstractSignatureTokenConnection;
import eu.europa.ec.markt.dss.signature.token.DSSPrivateKeyEntry;
import eu.europa.ec.markt.dss.signature.token.Pkcs12SignatureToken;

import java.security.PrivateKey;
import java.util.List;

/**
 * Implements PKCS12 signer.
 */
public class PKCS12Signer implements Signer {


  private final AbstractSignatureTokenConnection pkcs12SignatureToken;
  private DSSPrivateKeyEntry keyEntry;

  /**
   * Constructs PKCS12 signer object. If more than one key is provided only first is used
   *
   * @param fileName .p12 file name and path
   * @param password keystore password
   */
  public PKCS12Signer(String fileName, String password) {
    pkcs12SignatureToken = new Pkcs12SignatureToken(password, fileName);
    keyEntry = pkcs12SignatureToken.getKeys().get(0);
  }

  @Override
  public X509Cert getCertificate() {
    return new X509Cert(keyEntry.getCertificate());
  }

  @Override
  public final String getCity() {
    return null;
  }

  @Override
  public final String getCountryName() {
    return null;
  }

  @Override
  public final String getPostalCode() {
    return null;
  }

  @Override
  public void setSignatureProductionPlace(final String city, final String stateOrProvince, final String postalCode,
                                          final String countryName) {
  }

  @Override
  public final String getStateOrProvince() {
    return null;
  }

  @Override
  public final List<String> getSignerRoles() {
    return null;
  }

  @Override
  public void setSignerRoles(final List<String> signerRoles) {
  }

  @Override
  public final byte[] sign(final String method, final byte[] digest) throws Exception {
    return new byte[0];
  }

  @Override
  public final PrivateKey getPrivateKey() {
    return keyEntry.getPrivateKey();
  }

  @Override
  public byte[] sign(byte[] dataToSign, String digestAlgorithm) {
    return pkcs12SignatureToken.sign(dataToSign, DigestAlgorithm.forXML(digestAlgorithm), keyEntry);
  }


}
