CREATE TABLE vehicle (
    id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id  VARCHAR(10),
    make        VARCHAR(40),
    model       VARCHAR(40),
    model_year  VARCHAR(20),
    body_style  VARCHAR(255),
    engine      VARCHAR(40),
    drive_type  VARCHAR(40),
    color       VARCHAR(40),

    mpg INTEGER,

    exterior_features VARCHAR(4000),
    interior_features VARCHAR(4000),

    msrp FLOAT,
    savings FLOAT,
    final_price FLOAT
)
