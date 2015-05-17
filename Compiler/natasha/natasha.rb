#!/usr/bin/env ruby
# encoding: utf-8

class RegexGrouper
  
  def initialize(regex, type)
    @regex = regex
    @type = type
  end

  def type
    @type
  end

  def regex
    @regex
  end
end

class Lexer
  RESERVED = 'RESERVED'
  INT      = 'INT'
  ID       = 'ID'
  NONE     = 'NONE'

  VALID_REGEX = [
    RegexGrouper.new(/![^\n]*/, NONE),
    RegexGrouper.new(/[ \n\t]+/, NONE),
    RegexGrouper.new(/%/, RESERVED),
    RegexGrouper.new(/-/, RESERVED),
  ]

  def self.start_tokenization(file_name)
    File.open(file_name, "r") do |f|
        line_number = 0
        pattern_index = 0
        match = nil
        f.each_line do |line|
          line_length = line.length
          VALID_REGEX.each do |pattern|
            match = line[pattern_index..line_length].match pattern.regex

            if match
              if pattern.type != NONE
                puts pattern.type
              end
              break
            end
            
          end

          line_number += 1

          if match.nil?
            puts "Error happened on line #{line_number}"
            exit
          else
          end
        end
    end
  end
end

class CLI

  def start(file_name)

    # Make sure that user provided file name
    if file_name.nil?
      puts "\e[31m Filename must be provided \e[0m"
      return
    end

    # Check if the file is a valid natasaha source file
    if !file_name.end_with?(".natasha")
      puts "\e[31m Not a valid natasha source file \e[0m"
      return
    end

    # Check whether the file exist
    if !File.exists?(file_name)
      puts "\e[31m The file you specified does not exists \e[0m"
      return
    end

    # If we get here it means that our file is valid so we just want to
    # process it
    process_file(file_name)
  end

  def process_file(file_name)
    Lexer.start_tokenization(file_name)
  end

end

cli = CLI.new
file_name = ARGV[0]
cli.start(file_name)
