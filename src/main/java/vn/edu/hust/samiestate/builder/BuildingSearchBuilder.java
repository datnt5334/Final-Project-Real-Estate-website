package vn.edu.hust.samiestate.builder;

public class BuildingSearchBuilder {

	private String name;
	private Integer floorArea;
	private String ward;
	private String street;
	private Integer numberOfBasement;
	private String direction;
	private String level;
	private String managerName;
	private String managerPhone;
	private Long staffId;
	private Integer areaRentFrom;
	private Integer areaRentTo;
	private Integer costRentFrom;
	private Integer costRentTo;
	private String districtCode;

	public String getName() {
		return name;
	}

	public Integer getFloorArea() {
		return floorArea;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public String getManagerName() {
		return managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public Long getStaffId() {
		return staffId;
	}

	public Integer getAreaRentFrom() {
		return areaRentFrom;
	}

	public Integer getAreaRentTo() {
		return areaRentTo;
	}

	public Integer getCostRentFrom() {
		return costRentFrom;
	}

	public Integer getCostRentTo() {
		return costRentTo;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.direction = builder.direction;
		this.level = builder.level;
		this.managerName = builder.managerName;
		this.managerPhone = builder.managerPhone;
		this.staffId = builder.staffId;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.districtCode = builder.districtCode;
	}
	
	public static class Builder {

		private String name;
		private Integer floorArea;
		private String ward;
		private String street;
		private Integer numberOfBasement;
		private String direction;
		private String level;
		private String managerName;
		private String managerPhone;
		private Long staffId;
		private Integer areaRentFrom;
		private Integer areaRentTo;
		private Integer costRentFrom;
		private Integer costRentTo;
		private String districtCode;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setFloorArea(Integer floorArea) {
			this.floorArea = floorArea;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setNumberOfBasement(Integer numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setManagerName(String managerName) {
			this.managerName = managerName;
			return this;
		}

		public Builder setManagerPhone(String managerPhone) {
			this.managerPhone = managerPhone;
			return this;
		}

		public Builder setStaffId(Long staffId) {
			this.staffId = staffId;
			return this;
		}

		public Builder setAreaRentFrom(Integer areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(Integer areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}

		public Builder setCostRentFrom(Integer costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(Integer costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setDistrictCode(String districtCode) {
			this.districtCode = districtCode;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
