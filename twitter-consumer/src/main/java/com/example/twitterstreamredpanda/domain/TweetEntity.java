/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example.twitterstreamredpanda.domain;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class TweetEntity extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4259911304437535230L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TweetEntity\",\"namespace\":\"com.example.twitterstreamredpanda.domain\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"text\",\"type\":\"string\"},{\"name\":\"createdAt\",\"type\":\"int\",\"logicalType\":\"date\"},{\"name\":\"fromUser\",\"type\":\"string\"},{\"name\":\"languageCode\",\"type\":\"string\"},{\"name\":\"source\",\"type\":\"string\"},{\"name\":\"retweetCount\",\"type\":\"int\"},{\"name\":\"retweeted\",\"type\":\"boolean\"},{\"name\":\"favorited\",\"type\":\"boolean\"},{\"name\":\"favoriteCount\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TweetEntity> ENCODER =
      new BinaryMessageEncoder<TweetEntity>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TweetEntity> DECODER =
      new BinaryMessageDecoder<TweetEntity>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TweetEntity> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TweetEntity> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TweetEntity>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TweetEntity to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TweetEntity from a ByteBuffer. */
  public static TweetEntity fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public int id;
  @Deprecated public java.lang.CharSequence text;
  @Deprecated public int createdAt;
  @Deprecated public java.lang.CharSequence fromUser;
  @Deprecated public java.lang.CharSequence languageCode;
  @Deprecated public java.lang.CharSequence source;
  @Deprecated public int retweetCount;
  @Deprecated public boolean retweeted;
  @Deprecated public boolean favorited;
  @Deprecated public int favoriteCount;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TweetEntity() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param text The new value for text
   * @param createdAt The new value for createdAt
   * @param fromUser The new value for fromUser
   * @param languageCode The new value for languageCode
   * @param source The new value for source
   * @param retweetCount The new value for retweetCount
   * @param retweeted The new value for retweeted
   * @param favorited The new value for favorited
   * @param favoriteCount The new value for favoriteCount
   */
  public TweetEntity(java.lang.Integer id, java.lang.CharSequence text, java.lang.Integer createdAt, java.lang.CharSequence fromUser, java.lang.CharSequence languageCode, java.lang.CharSequence source, java.lang.Integer retweetCount, java.lang.Boolean retweeted, java.lang.Boolean favorited, java.lang.Integer favoriteCount) {
    this.id = id;
    this.text = text;
    this.createdAt = createdAt;
    this.fromUser = fromUser;
    this.languageCode = languageCode;
    this.source = source;
    this.retweetCount = retweetCount;
    this.retweeted = retweeted;
    this.favorited = favorited;
    this.favoriteCount = favoriteCount;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return text;
    case 2: return createdAt;
    case 3: return fromUser;
    case 4: return languageCode;
    case 5: return source;
    case 6: return retweetCount;
    case 7: return retweeted;
    case 8: return favorited;
    case 9: return favoriteCount;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: text = (java.lang.CharSequence)value$; break;
    case 2: createdAt = (java.lang.Integer)value$; break;
    case 3: fromUser = (java.lang.CharSequence)value$; break;
    case 4: languageCode = (java.lang.CharSequence)value$; break;
    case 5: source = (java.lang.CharSequence)value$; break;
    case 6: retweetCount = (java.lang.Integer)value$; break;
    case 7: retweeted = (java.lang.Boolean)value$; break;
    case 8: favorited = (java.lang.Boolean)value$; break;
    case 9: favoriteCount = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.Integer getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.Integer value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'text' field.
   * @return The value of the 'text' field.
   */
  public java.lang.CharSequence getText() {
    return text;
  }

  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.CharSequence value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.lang.Integer getCreatedAt() {
    return createdAt;
  }

  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.lang.Integer value) {
    this.createdAt = value;
  }

  /**
   * Gets the value of the 'fromUser' field.
   * @return The value of the 'fromUser' field.
   */
  public java.lang.CharSequence getFromUser() {
    return fromUser;
  }

  /**
   * Sets the value of the 'fromUser' field.
   * @param value the value to set.
   */
  public void setFromUser(java.lang.CharSequence value) {
    this.fromUser = value;
  }

  /**
   * Gets the value of the 'languageCode' field.
   * @return The value of the 'languageCode' field.
   */
  public java.lang.CharSequence getLanguageCode() {
    return languageCode;
  }

  /**
   * Sets the value of the 'languageCode' field.
   * @param value the value to set.
   */
  public void setLanguageCode(java.lang.CharSequence value) {
    this.languageCode = value;
  }

  /**
   * Gets the value of the 'source' field.
   * @return The value of the 'source' field.
   */
  public java.lang.CharSequence getSource() {
    return source;
  }

  /**
   * Sets the value of the 'source' field.
   * @param value the value to set.
   */
  public void setSource(java.lang.CharSequence value) {
    this.source = value;
  }

  /**
   * Gets the value of the 'retweetCount' field.
   * @return The value of the 'retweetCount' field.
   */
  public java.lang.Integer getRetweetCount() {
    return retweetCount;
  }

  /**
   * Sets the value of the 'retweetCount' field.
   * @param value the value to set.
   */
  public void setRetweetCount(java.lang.Integer value) {
    this.retweetCount = value;
  }

  /**
   * Gets the value of the 'retweeted' field.
   * @return The value of the 'retweeted' field.
   */
  public java.lang.Boolean getRetweeted() {
    return retweeted;
  }

  /**
   * Sets the value of the 'retweeted' field.
   * @param value the value to set.
   */
  public void setRetweeted(java.lang.Boolean value) {
    this.retweeted = value;
  }

  /**
   * Gets the value of the 'favorited' field.
   * @return The value of the 'favorited' field.
   */
  public java.lang.Boolean getFavorited() {
    return favorited;
  }

  /**
   * Sets the value of the 'favorited' field.
   * @param value the value to set.
   */
  public void setFavorited(java.lang.Boolean value) {
    this.favorited = value;
  }

  /**
   * Gets the value of the 'favoriteCount' field.
   * @return The value of the 'favoriteCount' field.
   */
  public java.lang.Integer getFavoriteCount() {
    return favoriteCount;
  }

  /**
   * Sets the value of the 'favoriteCount' field.
   * @param value the value to set.
   */
  public void setFavoriteCount(java.lang.Integer value) {
    this.favoriteCount = value;
  }

  /**
   * Creates a new TweetEntity RecordBuilder.
   * @return A new TweetEntity RecordBuilder
   */
  public static com.example.twitterstreamredpanda.domain.TweetEntity.Builder newBuilder() {
    return new com.example.twitterstreamredpanda.domain.TweetEntity.Builder();
  }

  /**
   * Creates a new TweetEntity RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TweetEntity RecordBuilder
   */
  public static com.example.twitterstreamredpanda.domain.TweetEntity.Builder newBuilder(com.example.twitterstreamredpanda.domain.TweetEntity.Builder other) {
    return new com.example.twitterstreamredpanda.domain.TweetEntity.Builder(other);
  }

  /**
   * Creates a new TweetEntity RecordBuilder by copying an existing TweetEntity instance.
   * @param other The existing instance to copy.
   * @return A new TweetEntity RecordBuilder
   */
  public static com.example.twitterstreamredpanda.domain.TweetEntity.Builder newBuilder(com.example.twitterstreamredpanda.domain.TweetEntity other) {
    return new com.example.twitterstreamredpanda.domain.TweetEntity.Builder(other);
  }

  /**
   * RecordBuilder for TweetEntity instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TweetEntity>
    implements org.apache.avro.data.RecordBuilder<TweetEntity> {

    private int id;
    private java.lang.CharSequence text;
    private int createdAt;
    private java.lang.CharSequence fromUser;
    private java.lang.CharSequence languageCode;
    private java.lang.CharSequence source;
    private int retweetCount;
    private boolean retweeted;
    private boolean favorited;
    private int favoriteCount;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.twitterstreamredpanda.domain.TweetEntity.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[2].schema(), other.createdAt);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.fromUser)) {
        this.fromUser = data().deepCopy(fields()[3].schema(), other.fromUser);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.languageCode)) {
        this.languageCode = data().deepCopy(fields()[4].schema(), other.languageCode);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.source)) {
        this.source = data().deepCopy(fields()[5].schema(), other.source);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.retweetCount)) {
        this.retweetCount = data().deepCopy(fields()[6].schema(), other.retweetCount);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.retweeted)) {
        this.retweeted = data().deepCopy(fields()[7].schema(), other.retweeted);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.favorited)) {
        this.favorited = data().deepCopy(fields()[8].schema(), other.favorited);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.favoriteCount)) {
        this.favoriteCount = data().deepCopy(fields()[9].schema(), other.favoriteCount);
        fieldSetFlags()[9] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TweetEntity instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.twitterstreamredpanda.domain.TweetEntity other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.text)) {
        this.text = data().deepCopy(fields()[1].schema(), other.text);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[2].schema(), other.createdAt);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.fromUser)) {
        this.fromUser = data().deepCopy(fields()[3].schema(), other.fromUser);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.languageCode)) {
        this.languageCode = data().deepCopy(fields()[4].schema(), other.languageCode);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.source)) {
        this.source = data().deepCopy(fields()[5].schema(), other.source);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.retweetCount)) {
        this.retweetCount = data().deepCopy(fields()[6].schema(), other.retweetCount);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.retweeted)) {
        this.retweeted = data().deepCopy(fields()[7].schema(), other.retweeted);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.favorited)) {
        this.favorited = data().deepCopy(fields()[8].schema(), other.favorited);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.favoriteCount)) {
        this.favoriteCount = data().deepCopy(fields()[9].schema(), other.favoriteCount);
        fieldSetFlags()[9] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.Integer getId() {
      return id;
    }

    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'text' field.
      * @return The value.
      */
    public java.lang.CharSequence getText() {
      return text;
    }

    /**
      * Sets the value of the 'text' field.
      * @param value The value of 'text'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setText(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.text = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasText() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'text' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearText() {
      text = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.lang.Integer getCreatedAt() {
      return createdAt;
    }

    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setCreatedAt(int value) {
      validate(fields()[2], value);
      this.createdAt = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearCreatedAt() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'fromUser' field.
      * @return The value.
      */
    public java.lang.CharSequence getFromUser() {
      return fromUser;
    }

    /**
      * Sets the value of the 'fromUser' field.
      * @param value The value of 'fromUser'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setFromUser(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.fromUser = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'fromUser' field has been set.
      * @return True if the 'fromUser' field has been set, false otherwise.
      */
    public boolean hasFromUser() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'fromUser' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearFromUser() {
      fromUser = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'languageCode' field.
      * @return The value.
      */
    public java.lang.CharSequence getLanguageCode() {
      return languageCode;
    }

    /**
      * Sets the value of the 'languageCode' field.
      * @param value The value of 'languageCode'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setLanguageCode(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.languageCode = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'languageCode' field has been set.
      * @return True if the 'languageCode' field has been set, false otherwise.
      */
    public boolean hasLanguageCode() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'languageCode' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearLanguageCode() {
      languageCode = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'source' field.
      * @return The value.
      */
    public java.lang.CharSequence getSource() {
      return source;
    }

    /**
      * Sets the value of the 'source' field.
      * @param value The value of 'source'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setSource(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.source = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'source' field has been set.
      * @return True if the 'source' field has been set, false otherwise.
      */
    public boolean hasSource() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'source' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearSource() {
      source = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'retweetCount' field.
      * @return The value.
      */
    public java.lang.Integer getRetweetCount() {
      return retweetCount;
    }

    /**
      * Sets the value of the 'retweetCount' field.
      * @param value The value of 'retweetCount'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setRetweetCount(int value) {
      validate(fields()[6], value);
      this.retweetCount = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'retweetCount' field has been set.
      * @return True if the 'retweetCount' field has been set, false otherwise.
      */
    public boolean hasRetweetCount() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'retweetCount' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearRetweetCount() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'retweeted' field.
      * @return The value.
      */
    public java.lang.Boolean getRetweeted() {
      return retweeted;
    }

    /**
      * Sets the value of the 'retweeted' field.
      * @param value The value of 'retweeted'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setRetweeted(boolean value) {
      validate(fields()[7], value);
      this.retweeted = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'retweeted' field has been set.
      * @return True if the 'retweeted' field has been set, false otherwise.
      */
    public boolean hasRetweeted() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'retweeted' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearRetweeted() {
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'favorited' field.
      * @return The value.
      */
    public java.lang.Boolean getFavorited() {
      return favorited;
    }

    /**
      * Sets the value of the 'favorited' field.
      * @param value The value of 'favorited'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setFavorited(boolean value) {
      validate(fields()[8], value);
      this.favorited = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'favorited' field has been set.
      * @return True if the 'favorited' field has been set, false otherwise.
      */
    public boolean hasFavorited() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'favorited' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearFavorited() {
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'favoriteCount' field.
      * @return The value.
      */
    public java.lang.Integer getFavoriteCount() {
      return favoriteCount;
    }

    /**
      * Sets the value of the 'favoriteCount' field.
      * @param value The value of 'favoriteCount'.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder setFavoriteCount(int value) {
      validate(fields()[9], value);
      this.favoriteCount = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'favoriteCount' field has been set.
      * @return True if the 'favoriteCount' field has been set, false otherwise.
      */
    public boolean hasFavoriteCount() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'favoriteCount' field.
      * @return This builder.
      */
    public com.example.twitterstreamredpanda.domain.TweetEntity.Builder clearFavoriteCount() {
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TweetEntity build() {
      try {
        TweetEntity record = new TweetEntity();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.text = fieldSetFlags()[1] ? this.text : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.createdAt = fieldSetFlags()[2] ? this.createdAt : (java.lang.Integer) defaultValue(fields()[2]);
        record.fromUser = fieldSetFlags()[3] ? this.fromUser : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.languageCode = fieldSetFlags()[4] ? this.languageCode : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.source = fieldSetFlags()[5] ? this.source : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.retweetCount = fieldSetFlags()[6] ? this.retweetCount : (java.lang.Integer) defaultValue(fields()[6]);
        record.retweeted = fieldSetFlags()[7] ? this.retweeted : (java.lang.Boolean) defaultValue(fields()[7]);
        record.favorited = fieldSetFlags()[8] ? this.favorited : (java.lang.Boolean) defaultValue(fields()[8]);
        record.favoriteCount = fieldSetFlags()[9] ? this.favoriteCount : (java.lang.Integer) defaultValue(fields()[9]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TweetEntity>
    WRITER$ = (org.apache.avro.io.DatumWriter<TweetEntity>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TweetEntity>
    READER$ = (org.apache.avro.io.DatumReader<TweetEntity>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
